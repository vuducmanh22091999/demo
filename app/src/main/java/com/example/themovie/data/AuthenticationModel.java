package com.example.themovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AuthenticationModel implements Parcelable {
    @SerializedName("success")
    private boolean success;
    @SerializedName("expires_at")
    private String expires_ad;
    @SerializedName("request_token")
    private String request_token;

    public AuthenticationModel(boolean success, String expires_ad, String request_token) {
        this.success = success;
        this.expires_ad = expires_ad;
        this.request_token = request_token;
    }

    protected AuthenticationModel(Parcel in) {
        success = in.readByte() != 0;
        expires_ad = in.readString();
        request_token = in.readString();
    }

    public static final Creator<AuthenticationModel> CREATOR = new Creator<AuthenticationModel>() {
        @Override
        public AuthenticationModel createFromParcel(Parcel in) {
            return new AuthenticationModel(in);
        }

        @Override
        public AuthenticationModel[] newArray(int size) {
            return new AuthenticationModel[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpires_ad() {
        return expires_ad;
    }

    public void setExpires_ad(String expires_ad) {
        this.expires_ad = expires_ad;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (success ? 1 : 0));
        parcel.writeString(expires_ad);
        parcel.writeString(request_token);
    }
}
