package com.example.themovie.data;

import com.example.themovie.adapter.DataListMovies;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListPopular {
    @SerializedName("results")
    ArrayList<DataListMovies> dataListPopularMovies;

    public ArrayList<DataListMovies> getPopularModelList() {
        return dataListPopularMovies;
    }
}
