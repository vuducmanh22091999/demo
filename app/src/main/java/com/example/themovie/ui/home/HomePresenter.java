package com.example.themovie.ui.home;

import android.util.Log;

import com.example.themovie.R;
import com.example.themovie.api.HomeApi;
import com.example.themovie.data.AuthenticationModel;
import com.example.themovie.data.Constant;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.ListPopular;
import com.example.themovie.data.RetrofitClient;
import com.example.themovie.data.SessionModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter  {
    private HomeContract.View view;
    private RetrofitClient retrofitClient;
    HomeApi homeApi;

    public void setView(HomeContract.View view) {
        this.view = view;
        retrofitClient = new RetrofitClient();
        homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
    }

    @Override
    public void getListMovies(String apiKey) {
        homeApi.getListMovies(apiKey).enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                if (response.isSuccessful()) {
                    view.sendListMovies(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {

            }
        });
    }

    @Override
    public void getListPopular() {
        homeApi.getListPopular(Constant.API_KEY).enqueue(new Callback<ListPopular>() {
            @Override
            public void onResponse(Call<ListPopular> call, Response<ListPopular> response) {
                if (response.isSuccessful()) {
                    view.sendListPopular(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListPopular> call, Throwable t) {
                Log.d("demo", t.getMessage());
            }
        });
    }

    @Override
    public void getAuthentication(String apiKey) {
//        homeApi.getAuthenticationRx(apiKey)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        response -> handleAuthentication(response),
//                        error -> view.sendError(error.getMessage())
//                );

        homeApi.getAuthenticationRx(apiKey)
                .flatMap(authenticationModel -> callSession(authenticationModel.getRequest_token(), apiKey)
                        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.sendSession(response),
                        error -> view.sendError(error.getMessage())
                );

    }

    private Observable<SessionModel> callSession (String authen, String apiKey) {
        HashMap<String, String> map = new HashMap<>();
        map.put("request_token", authen);
        return homeApi.getSessionId(map, apiKey);
    }

    @Override
    public void getSessionId(HashMap<String, String> map, String apiKey) {
        homeApi.getSessionId(map, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.sendSession(response),
                        error -> view.sendError(error.getMessage())
                );
    }


    private void handleAuthentication(AuthenticationModel authenticationModel){
        view.sendAuthentication(authenticationModel);
    }
}
