package com.example.themovie.api;

import com.example.themovie.data.AuthenticationModel;
import com.example.themovie.data.ListKeyword;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.ListPeople;
import com.example.themovie.data.ListPopular;
import com.example.themovie.data.ListResultVideo;
import com.example.themovie.data.SessionModel;


import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeApi {
    @GET("movie/popular")
    Call<ListPopular> getListPopular(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<ListMovies> getListMovies(@Query("api_key") String api_key);

    @GET("movie/{movie_id}/keywords")
    Call<ListKeyword> getKeyword(@Path("movie_id") int id, @Query("api_key") String api_key);

    @GET("search/movie")
    Call<ListMovies> searchMovies(@Query("api_key") String api_key, @Query("query") String query);

    @GET("search/person")
    Call<ListPeople> searchPeople(@Query("api_key") String api_key, @Query("query") String query);

    //rxjava
    @GET("movie/{movie_id}/videos")
    Observable<ListResultVideo> getVideoMoviesRx(@Path("movie_id") int id, @Query("api_key") String api_key);

    @GET("search/movie")
    Observable<ListMovies> searchMoviesRx(@Query("api_key") String api_key, @Query("query") String query);

    @GET("search/person")
    Observable<ListPeople> searchPeopleRx(@Query("api_key") String api_key, @Query("query") String query);

    @GET("authentication/token/new")
    Observable<AuthenticationModel> getAuthenticationRx(@Query("api_key") String api_key);

    @POST("authentication/session/new")
    Observable<SessionModel> getSessionId (@Body HashMap<String, String> map, @Query("api_key") String api_key);
}
