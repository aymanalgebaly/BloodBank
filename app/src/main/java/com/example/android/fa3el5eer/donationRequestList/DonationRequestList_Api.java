package com.example.android.fa3el5eer.donationRequestList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface DonationRequestList_Api {

    @GET()
    Call<DonationListResponse> getDonationListResponse(@Url String url,@Query("api_token")String api_token);
}
