package com.example.themovie.ui.detail;

import com.example.themovie.R;
import com.example.themovie.api.HomeApi;
import com.example.themovie.data.Constant;
import com.example.themovie.data.ListKeyword;
import com.example.themovie.data.ListResultVideo;
import com.example.themovie.data.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMoviesPresenter implements DetailsMoviesContract.Presenter {
    private DetailsMoviesContract.View view;
    private RetrofitClient retrofitClient;
    private HomeApi homeApi;
    private CompositeDisposable compositeDisposable;

    public void setView(DetailsMoviesContract.View view) {
        this.view = view;
        retrofitClient = new RetrofitClient();
        homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getKeywordApi(int id) {
        homeApi.getKeyword(id, Constant.API_KEY).enqueue(new Callback<ListKeyword>() {
            @Override
            public void onResponse(Call<ListKeyword> call, Response<ListKeyword> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    view.sendKeyWordApi(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListKeyword> call, Throwable t) {

            }
        });
    }

    @Override
    public void getVideoRx(int id) {
        compositeDisposable.add(
                homeApi.getVideoMoviesRx(id, Constant.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        respone -> handleUrlVideo(respone, id)
                        ,
                        error -> view.sendError(error.getMessage())
                )
        );
    }

    private void handleUrlVideo(ListResultVideo listResultVideo, int idMovies){
        view.sendVideoRx(listResultVideo.getVideoModelArrayList().get(0).getKey(), idMovies);
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }



}
