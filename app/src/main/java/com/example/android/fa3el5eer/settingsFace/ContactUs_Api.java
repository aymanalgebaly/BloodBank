package com.example.android.fa3el5eer.settingsFace;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ContactUs_Api {

    @GET("settings")
    Call<ContactUsResponse>getContactUsResponse(@Query("url") String url,@Query("api_token") String api_token);

    @FormUrlEncoded
    @POST("contact")
    Call<ContactWithUsResponse> setSettingsContact(
            @Field("api_token") String api_token,
            @Field("title") String title,
            @Field("message") String message
    );

}
