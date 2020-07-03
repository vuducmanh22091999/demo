package com.example.themovie.interfaces;

import com.example.themovie.data.RoomMovies;

public interface UpdateMoviesListener {
    void onClickUpdateMovies(RoomMovies roomMovies);
    void onClickCancelUpdateMovies();
}
