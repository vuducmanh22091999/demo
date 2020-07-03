package com.example.themovie.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.themovie.data.RoomMovies;
import com.example.themovie.interfaces.DAOMovies;

@Database(entities = {RoomMovies.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DAOMovies getDAOMovies();
}
