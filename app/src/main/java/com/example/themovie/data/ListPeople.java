package com.example.themovie.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListPeople {
    @SerializedName("results")
    private ArrayList<PeopleModel> peopleModelArrayList;

    public ArrayList<PeopleModel> getPeopleModelArrayList() {
        return peopleModelArrayList;
    }
}
