package com.example.android.fa3el5eer.gavernorates;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Gavernorates_Api {

    @GET("governorates")
    Call<GavernoratesResponse> getGavernoratesResponse();
}
