package com.example.themovie.ui.search;

import com.example.themovie.data.ListPeople;
import com.example.themovie.data.PeopleModel;

import java.util.ArrayList;

public interface SearchPeopleContract {
    interface View {
        void sendSearchApiPeople(ArrayList<PeopleModel> peopleModelArrayList);
        void sendError (String messageError);
    }

    interface  Presenter {
        void getSearchApiPeople (String query);
    }
}
