package com.example.android.fa3el5eer.reset_password;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ResetPassword_Api {

    @FormUrlEncoded
    @POST("reset-password")
    Call<ResetPasswordResponse>getResetPasswordResponse(@Field("phone") String phone);
}
