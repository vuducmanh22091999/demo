package com.example.themovie.adapter;

public class DataListPopularSearchMovies {
    private int posterMovies;
    private String nameMovies;
    private String timeOfMovies;
    private String categoriesMovies;
    private String nameDirector;
    private String pointEvaluate;
    private String numberReviews;

    public DataListPopularSearchMovies(int posterMovies, String nameMovies, String timeOfMovies, String categoriesMovies, String nameDirector, String pointEvaluate, String numberReviews) {
        this.posterMovies = posterMovies;
        this.nameMovies = nameMovies;
        this.timeOfMovies = timeOfMovies;
        this.categoriesMovies = categoriesMovies;
        this.nameDirector = nameDirector;
        this.pointEvaluate = pointEvaluate;
        this.numberReviews = numberReviews;
    }

    public int getPosterMovies() {
        return posterMovies;
    }

    public void setPosterMovies(int posterMovies) {
        this.posterMovies = posterMovies;
    }

    public String getNameMovies() {
        return nameMovies;
    }

    public void setNameMovies(String nameMovies) {
        this.nameMovies = nameMovies;
    }

    public String getTimeOfMovies() {
        return timeOfMovies;
    }

    public void setTimeOfMovies(String timeOfMovies) {
        this.timeOfMovies = timeOfMovies;
    }

    public String getCategoriesMovies() {
        return categoriesMovies;
    }

    public void setCategoriesMovies(String categoriesMovies) {
        this.categoriesMovies = categoriesMovies;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public void setNameDirector(String nameDirector) {
        this.nameDirector = nameDirector;
    }

    public String getPointEvaluate() {
        return pointEvaluate;
    }

    public void setPointEvaluate(String pointEvaluate) {
        this.pointEvaluate = pointEvaluate;
    }

    public String getNumberReviews() {
        return numberReviews;
    }

    public void setNumberReviews(String numberReviews) {
        this.numberReviews = numberReviews;
    }
}
