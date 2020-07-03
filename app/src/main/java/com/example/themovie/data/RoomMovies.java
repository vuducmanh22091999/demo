package com.example.themovie.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class RoomMovies {
    @PrimaryKey
    @NonNull private long idMovies;
    private String titleMovies;

    public long getIdMovies() {
        return idMovies;
    }

    public void setIdMovies(long idMovies) {
        this.idMovies = idMovies;
    }

    public String getTitleMovies() {
        return titleMovies;
    }

    public void setTitleMovies(String titleMovies) {
        this.titleMovies = titleMovies;
    }
}
