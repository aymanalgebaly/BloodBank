package com.example.android.fa3el5eer.postsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Posts_Api {

    @GET("posts")
    Call<PostsListResponse> getPostsResponse(@Query("api_token") String api_token);



}
