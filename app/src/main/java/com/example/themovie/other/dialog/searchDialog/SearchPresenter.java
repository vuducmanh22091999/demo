package com.example.themovie.other.dialog.searchDialog;

import com.example.themovie.api.HomeApi;
import com.example.themovie.data.Constant;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.ListPeople;
import com.example.themovie.data.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private RetrofitClient retrofitClient;
    private HomeApi homeApi;
    private CompositeDisposable compositeDisposable;

    public void setView(SearchContract.View view) {
        this.view = view;
        retrofitClient = new RetrofitClient();
        homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void handleSearchMovies(String query) {
        compositeDisposable.add(
                homeApi.searchMoviesRx(Constant.API_KEY, query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> handleDataMovies(response),
                                error -> view.getError(error.getMessage())
                        )
        );
    }

    @Override
    public void handleSearchPeople(String query) {
        compositeDisposable.add(
                homeApi.searchPeopleRx(Constant.API_KEY, query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> handleDataPeople(response),
                                error -> view.getError(error.getMessage())
                        )
        );
    }

    public void clearCompositeDisposable() {
        compositeDisposable.clear();
    }

    private void handleDataMovies(ListMovies listMovies) {
        view.getSearchMovies(listMovies.getDataListMovies());
    }

    private void handleDataPeople(ListPeople listPeople) {
        view.getSearchPeople(listPeople.getPeopleModelArrayList());
    }
}
