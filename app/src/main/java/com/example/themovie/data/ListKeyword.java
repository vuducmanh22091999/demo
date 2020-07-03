package com.example.themovie.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListKeyword {
    @SerializedName("keywords")
    private ArrayList<KeywordModel> keywordModelArrayList;

    public ArrayList<KeywordModel> getKeywordModelArrayList() {
        return keywordModelArrayList;
    }
}
