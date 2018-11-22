package com.example.android.fa3el5eer.posts;

import com.example.android.fa3el5eer.postsList.PostsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PostsList_Api {

    @GET("post")
    Call<PostsDetailsResponse> getPostsListResponse(@Query("api_token") String api_token,
                                                 @Query("post_id")int id);


}
