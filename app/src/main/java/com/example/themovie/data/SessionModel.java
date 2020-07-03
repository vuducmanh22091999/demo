package com.example.themovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SessionModel implements Parcelable {
    @SerializedName("failure")
    private boolean failure;
    @SerializedName("status_code")
    private int status_code;
    @SerializedName("status_message")
    private String status_message;

    public SessionModel(boolean failure, int status_code, String status_message) {
        this.failure = failure;
        this.status_code = status_code;
        this.status_message = status_message;
    }

    protected SessionModel(Parcel in) {
        failure = in.readByte() != 0;
        status_code = in.readInt();
        status_message = in.readString();
    }

    public static final Creator<SessionModel> CREATOR = new Creator<SessionModel>() {
        @Override
        public SessionModel createFromParcel(Parcel in) {
            return new SessionModel(in);
        }

        @Override
        public SessionModel[] newArray(int size) {
            return new SessionModel[size];
        }
    };

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (failure ? 1 : 0));
        parcel.writeInt(status_code);
        parcel.writeString(status_message);
    }
}
