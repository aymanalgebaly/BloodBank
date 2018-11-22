
package com.example.android.fa3el5eer.favourites_articals;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavouritesResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private FavouritesData favouritesData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FavouritesData getFavouritesData() {
        return favouritesData;
    }

    public void setData(FavouritesData favouritesData) {
        this.favouritesData = favouritesData;
    }

}
