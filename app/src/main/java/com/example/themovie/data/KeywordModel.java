package com.example.themovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class KeywordModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    protected KeywordModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<KeywordModel> CREATOR = new Creator<KeywordModel>() {
        @Override
        public KeywordModel createFromParcel(Parcel in) {
            return new KeywordModel(in);
        }

        @Override
        public KeywordModel[] newArray(int size) {
            return new KeywordModel[size];
        }
    };

    public int getId() {
        return id;
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
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
