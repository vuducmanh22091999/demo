package com.example.themovie.other.dialog.searchDialog;

import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.PeopleModel;

import java.util.ArrayList;

public interface SearchContract {
    interface View {
        void getSearchMovies(ArrayList<DataListMovies> dataListMoviesArrayList);
        void getError (String messageError);
        void getSearchPeople(ArrayList<PeopleModel> peopleModelArrayList);
    }

    interface  Presenter {
        void handleSearchMovies (String query);
        void handleSearchPeople (String query);
    }
}
