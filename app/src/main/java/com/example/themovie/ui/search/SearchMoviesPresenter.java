package com.example.themovie.ui.search;

import com.example.themovie.api.HomeApi;
import com.example.themovie.data.Constant;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.RetrofitClient;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchMoviesPresenter implements SearchMoviesContract.Presenter {
    private SearchMoviesContract.View view;
    private RetrofitClient retrofitClient;
    private HomeApi homeApi;
    private CompositeDisposable compositeDisposable;

    public void setView(SearchMoviesContract.View view) {
        this.view = view;
        retrofitClient = new RetrofitClient();
        homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getSearchApiMovies(String query) {
        compositeDisposable.add(
                homeApi.searchMoviesRx(Constant.API_KEY, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> sendSearchMovies(response)
                        ,
                        error -> view.sendError(error.getMessage())
                )
        );
    }

    private void sendSearchMovies(ListMovies listMovies) {
        view.sendSearchApiMovies(listMovies.getDataListMovies());
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }
}
