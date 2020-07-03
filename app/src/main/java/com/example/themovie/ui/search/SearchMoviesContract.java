package com.example.themovie.ui.search;

import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.data.ListMovies;

import java.util.ArrayList;

public interface SearchMoviesContract {
    interface View {
        void sendSearchApiMovies(ArrayList<DataListMovies> dataListMoviesArrayList);
        void sendError (String messageError);
    }

    interface Presenter {
        void getSearchApiMovies (String query);
    }
}
