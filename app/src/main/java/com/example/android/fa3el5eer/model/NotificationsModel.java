package com.example.android.fa3el5eer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationsModel implements Parcelable{

    private TextView title,date;
    private ImageView alarm;

    protected NotificationsModel(Parcel in) {
    }

    public static final Creator<NotificationsModel> CREATOR = new Creator<NotificationsModel>() {
        @Override
        public NotificationsModel createFromParcel(Parcel in) {
            return new NotificationsModel(in);
        }

        @Override
        public NotificationsModel[] newArray(int size) {
            return new NotificationsModel[size];
        }
    };

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public ImageView getAlarm() {
        return alarm;
    }

    public void setAlarm(ImageView alarm) {
        this.alarm = alarm;
    }

    public NotificationsModel(TextView title, TextView date, ImageView alarm) {
        this.title = title;
        this.date = date;
        this.alarm = alarm;
    }

    public NotificationsModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
