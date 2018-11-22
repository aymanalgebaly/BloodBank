package com.example.android.fa3el5eer.report;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Report_api {

    @FormUrlEncoded
    @POST("report")
    Call<ReportResponse> setReportResponse(
            @Field("api_token") String api_token,
            @Field("message") String message
    );
}
