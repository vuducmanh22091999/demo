package com.example.themovie.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.themovie.data.Constant;
import com.google.gson.annotations.SerializedName;

public class DataListMovies implements Parcelable {
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("vote_count")
    private int vote_count;
    @SerializedName("video")
    private boolean video;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("id")
    private int id;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private float vote_average;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("media_type")
    private String media_type;

    protected DataListMovies(Parcel in) {
        popularity = in.readFloat();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        poster_path = in.readString();
        id = in.readInt();
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        title = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
        release_date = in.readString();
        media_type = in.readString();
    }

    public static final Creator<DataListMovies> CREATOR = new Creator<DataListMovies>() {
        @Override
        public DataListMovies createFromParcel(Parcel in) {
            return new DataListMovies(in);
        }

        @Override
        public DataListMovies[] newArray(int size) {
            return new DataListMovies[size];
        }
    };

    public float getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPoster_path() {
        return Constant.BASE_URL_IMG + poster_path;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMedia_type() {
        return media_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(popularity);
        parcel.writeInt(vote_count);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeString(poster_path);
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(backdrop_path);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(title);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}
