package com.example.themovie.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.themovie.data.RoomMovies;

import java.util.List;

@Dao
public interface DAOMovies {
    @Insert
    public void insertMovies(RoomMovies roomMovies);

    @Update
    public void updateMovies(RoomMovies roomMovies);

    @Delete
    public void deleteMovies(RoomMovies roomMovies);

    @Query("SELECT * FROM movies")
    public List<RoomMovies> getRoomMovies();

    @Query("SELECT * FROM movies WHERE idMovies = :idMovies")
    public RoomMovies getRoomMoviesById(long idMovies);

    @Query("SELECT * FROM movies WHERE idMovies LIKE :idMovies")
    public List<RoomMovies> getLikeById(String idMovies);
}
