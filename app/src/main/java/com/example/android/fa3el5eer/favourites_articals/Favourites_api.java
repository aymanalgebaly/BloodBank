package com.example.android.fa3el5eer.favourites_articals;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Favourites_api {

    @FormUrlEncoded
    @POST("post-toggle-favourite")
    Call<ToggleFavouritesResponse>getToggleFavouritesResponse(
            @Field("post_id") int id,
            @Field("api_token") String api_token
    );

    @GET()
    Call<FavouritesResponse> getFavouritesResponse(@Url String url ,@Query("api_token")String api_token);
}
