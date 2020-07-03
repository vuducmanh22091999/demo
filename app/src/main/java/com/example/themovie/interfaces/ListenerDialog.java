package com.example.themovie.interfaces;

import com.example.themovie.adapter.DataListMovies;

public interface ListenerDialog {
    void onClickClose();
    void onClickOk(DataListMovies dataListMovies);
    void onClickShowName(String name);
}
