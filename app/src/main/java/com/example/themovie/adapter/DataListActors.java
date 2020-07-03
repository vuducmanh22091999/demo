package com.example.themovie.adapter;

public class DataListActors {
    private int imageActors;
    private String nameActors;
    private String numberLikeActors;

    public DataListActors(int imageActors, String nameActors, String numberLikeActors) {
        this.imageActors = imageActors;
        this.nameActors = nameActors;
        this.numberLikeActors = numberLikeActors;
    }

    public int getImageActors() {
        return imageActors;
    }

    public void setImageActors(int imageActors) {
        this.imageActors = imageActors;
    }

    public String getNameActors() {
        return nameActors;
    }

    public void setNameActors(String nameActors) {
        this.nameActors = nameActors;
    }

    public String getNumberLikeActors() {
        return numberLikeActors;
    }

    public void setNumberLikeActors(String numberLikeActors) {
        this.numberLikeActors = numberLikeActors;
    }
}
