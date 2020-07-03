package com.example.themovie.data;

import com.example.themovie.adapter.DataListMovies;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMovies {
    @SerializedName("results")
    ArrayList<DataListMovies> dataListMovies;

    public ArrayList<DataListMovies> getDataListMovies() {
        return dataListMovies;
    }
}
