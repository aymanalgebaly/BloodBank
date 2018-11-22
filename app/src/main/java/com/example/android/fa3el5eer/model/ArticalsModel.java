package com.example.android.fa3el5eer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.fa3el5eer.adapter.ArticalsAdapter;

public class ArticalsModel implements Parcelable {

    private String id, text;
    private int image1, image2;


    public ArticalsModel(int image1, String text) {

        this.text = text;
        this.image1 = image1;

    }

    public ArticalsModel() {
    }

    protected ArticalsModel(Parcel in) {
        id = in.readString();
        text = in.readString();
        image1 = in.readInt();
        image2 = in.readInt();
    }

    public static final Creator<ArticalsModel> CREATOR = new Creator<ArticalsModel>() {
        @Override
        public ArticalsModel createFromParcel(Parcel in) {
            return new ArticalsModel(in);
        }

        @Override
        public ArticalsModel[] newArray(int size) {
            return new ArticalsModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeInt(image1);
        dest.writeInt(image2);
    }
}