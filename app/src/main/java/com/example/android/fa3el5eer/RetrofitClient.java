package com.example.android.fa3el5eer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getInstant(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://ipda3.com/blood-bank/api/v1/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit;
    }
}
