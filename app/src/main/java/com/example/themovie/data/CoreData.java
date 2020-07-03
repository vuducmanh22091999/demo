package com.example.themovie.data;

import com.example.themovie.R;
import com.example.themovie.adapter.DataCategories;
import com.example.themovie.adapter.DataListActors;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.DataListPopularMovies;
import com.example.themovie.adapter.DataListPopularSearchMovies;

import java.util.ArrayList;

public class CoreData {
   static public ArrayList<DataCategories> dataListCategories () {
        ArrayList<DataCategories> dataCategoriesArrayList = new ArrayList<>();
        dataCategoriesArrayList.add(new DataCategories("Add"));
        dataCategoriesArrayList.add(new DataCategories("Search"));
        dataCategoriesArrayList.add(new DataCategories("Show"));
        return dataCategoriesArrayList;
    }

    static public ArrayList<DataListPopularMovies> dataListPopularMovies () {
       ArrayList<DataListPopularMovies> dataListPopularMoviesArrayList = new ArrayList<>();
//        dataListPopularMoviesArrayList.add(new DataListPopularMovies(R.drawable.img_avengers, "Avengers"));
//        dataListPopularMoviesArrayList.add(new DataListPopularMovies(R.drawable.img_cohaugai, "Cô hầu gái"));
//        dataListPopularMoviesArrayList.add(new DataListPopularMovies(R.drawable.img_deadpool, "Deadpool"));
//        dataListPopularMoviesArrayList.add(new DataListPopularMovies(R.drawable.img_johnwick2, "John Wick 2"));
//        dataListPopularMoviesArrayList.add(new DataListPopularMovies(R.drawable.img_joker, "Joker"));
        return dataListPopularMoviesArrayList;
    }
    static public ArrayList<DataListMovies> dataListMovies () {
       ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_lordsofthefallen, "Lords of the fallen"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_matrix, "Matrix"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_parasite, "Parasite"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_starwars, "Start Wars"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_theassassin, "The Assassin"));
        return dataListMoviesArrayList;
    }

    static public ArrayList<DataListMovies> dataListRecentMovies () {
       ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_matrix, "Matrix"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_cohaugai, "Cô hầu gái"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_parasite, "Parasite"));
//        dataListMoviesArrayList.add(new DataListMovies(R.drawable.img_starwars, "Starwars"));
        return dataListMoviesArrayList;
    }

    static public ArrayList<DataListPopularSearchMovies> dataListPopularSearchMovies () {
       ArrayList<DataListPopularSearchMovies> dataListPopularSearchMoviesArrayList = new ArrayList<>();
//        dataListPopularSearchMoviesArrayList.add(new DataListPopularSearchMovies(R.drawable.img_avengers, "Avengers", "2h 30p", "Action", "ABC", "7.8", "100"));
//        dataListPopularSearchMoviesArrayList.add(new DataListPopularSearchMovies(R.drawable.img_deadpool, "Deadpool", "3h 30p", "Comedy", "DEF", "6.8", "1000"));
//        dataListPopularSearchMoviesArrayList.add(new DataListPopularSearchMovies(R.drawable.img_johnwick2, "John Wick 2", "1h 30p", "Adventure", "JKL", "8.8", "200"));
//        dataListPopularSearchMoviesArrayList.add(new DataListPopularSearchMovies(R.drawable.img_joker, "Joker", "4h 30p", "Action", "QWE", "10", "300"));
//        dataListPopularSearchMoviesArrayList.add(new DataListPopularSearchMovies(R.drawable.img_lordsofthefallen, "Lords Of The Fallen", "5h 30p", "Action", "ZXC", "4.8", "500"));
        return dataListPopularSearchMoviesArrayList;
    }

    static public ArrayList<DataListActors> dataListActors () {
       ArrayList<DataListActors> dataListActorsArrayList = new ArrayList<>();
        dataListActorsArrayList.add(new DataListActors(R.drawable.img_chris_evans, "Chris Evans", "100"));
        dataListActorsArrayList.add(new DataListActors(R.drawable.img_heath_ledger, "Heath Ledger", "200"));
        dataListActorsArrayList.add(new DataListActors(R.drawable.img_hugo_weaving, "Hugo Weaving", "300"));
        dataListActorsArrayList.add(new DataListActors(R.drawable.img_keanu_reeves, "Keanu Reeves", "400"));
        dataListActorsArrayList.add(new DataListActors(R.drawable.img_ryan_reynolds, "Ryan Reynolds", "500"));
        return dataListActorsArrayList;
    }

}
