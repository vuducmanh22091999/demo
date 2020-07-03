package com.example.themovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.themovie.adapter.DataListMovies;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PeopleModel implements Parcelable {
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("id")
    private int id;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("known_for_department")
    private String known_for_department;
    @SerializedName("gender")
    private int gender;
    @SerializedName("profile_path")
    private String profile_path;
    @SerializedName("known_for")
    private ArrayList<DataListMovies> known_for;
    @SerializedName("name")
    private String name;

    protected PeopleModel(Parcel in) {
        popularity = in.readFloat();
        id = in.readInt();
        adult = in.readByte() != 0;
        known_for_department = in.readString();
        gender = in.readInt();
        profile_path = in.readString();
        name = in.readString();
    }

    public static final Creator<PeopleModel> CREATOR = new Creator<PeopleModel>() {
        @Override
        public PeopleModel createFromParcel(Parcel in) {
            return new PeopleModel(in);
        }

        @Override
        public PeopleModel[] newArray(int size) {
            return new PeopleModel[size];
        }
    };

    public float getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public int getGender() {
        return gender;
    }

    public String getProfile_path() {
        return profile_path == null ? "" : Constant.BASE_URL_IMG + profile_path;
    }

    public ArrayList<DataListMovies> getKnown_for() {
        return known_for;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(popularity);
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(known_for_department);
        parcel.writeInt(gender);
        parcel.writeString(profile_path);
        parcel.writeString(name);
    }
}
