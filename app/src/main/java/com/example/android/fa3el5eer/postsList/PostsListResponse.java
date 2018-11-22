
package com.example.android.fa3el5eer.postsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostsListResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private PostsListData postsListData;

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

    public PostsListData getPostsListData() {
        return postsListData;
    }

    public void setData(PostsListData postsListData) {
        this.postsListData = postsListData;
    }
}
