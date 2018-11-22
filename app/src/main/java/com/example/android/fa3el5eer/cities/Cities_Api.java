package com.example.android.fa3el5eer.cities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Cities_Api {

    @GET("cities")
    Call<CitiesResponse> getCitiesResponse(
            @Query("governorate_id") int governorate_id
    );

    @GET("cities")
    Call<CitiesResponse> getAlltCitiesResponse(

    );
}
