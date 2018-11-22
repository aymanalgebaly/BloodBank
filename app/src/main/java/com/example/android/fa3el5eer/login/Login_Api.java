package com.example.android.fa3el5eer.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Login_Api {

    @FormUrlEncoded
    @POST("login")
    Call<Login>setLogin(
            @Field("phone") String phone,
            @Field("password") String pass);


}
