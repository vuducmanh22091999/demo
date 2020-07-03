package com.example.themovie.ui.home;

import android.se.omapi.Session;

import com.example.themovie.data.AuthenticationModel;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.ListPopular;
import com.example.themovie.data.SessionModel;

import java.util.HashMap;

public interface HomeContract {

    interface View {
        void sendListMovies(ListMovies listMovies);
        void sendListPopular(ListPopular listPopular);
        void sendAuthentication (AuthenticationModel authenticationModel);
        void sendSession (SessionModel sessionModel);
        void sendError (String messageError);
    }

    interface Presenter {
        void getListMovies(String apiKey);
        void getListPopular();
        void getAuthentication (String apiKey);
        void getSessionId (HashMap<String, String> map, String apiKey);
    }

}
