package com.example.themovie.ui.search;

import com.example.themovie.api.HomeApi;
import com.example.themovie.data.Constant;
import com.example.themovie.data.ListPeople;
import com.example.themovie.data.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPeoplePresenter implements SearchPeopleContract.Presenter {
    private SearchPeopleContract.View view;
    private RetrofitClient retrofitClient;
    private HomeApi homeApi;
    private CompositeDisposable compositeDisposable;

    public void setView(SearchPeopleContract.View view) {
        this.view = view;
        retrofitClient = new RetrofitClient();
        homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getSearchApiPeople(String query) {
        compositeDisposable.add(
                homeApi.searchPeopleRx(Constant.API_KEY, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> searchPeople(response)
                        ,
                        error -> view.sendError(error.getMessage())
                )
        );
    }

    private void searchPeople(ListPeople listPeople) {
        view.sendSearchApiPeople(listPeople.getPeopleModelArrayList());
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }
}
