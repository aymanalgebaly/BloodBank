package com.example.android.fa3el5eer.donationDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface DonationDetails_Api {

    @GET("donation-request")
    Call<DonationDetailsResponse> getDonationDetailsResponse(@Query("api_token") String api_token,
                                                             @Query("donation_id") int donation_id);
}
