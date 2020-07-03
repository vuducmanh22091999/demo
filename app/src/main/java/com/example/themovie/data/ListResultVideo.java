package com.example.themovie.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListResultVideo {
    @SerializedName("results")
    private ArrayList<VideoModel> videoModelArrayList;

    public ArrayList<VideoModel> getVideoModelArrayList() {
        return videoModelArrayList;
    }
}
