
package com.example.android.fa3el5eer.posts;

import com.example.android.fa3el5eer.posts.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostsDetailsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private com.example.android.fa3el5eer.posts.Data data;

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

    public com.example.android.fa3el5eer.posts.Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
