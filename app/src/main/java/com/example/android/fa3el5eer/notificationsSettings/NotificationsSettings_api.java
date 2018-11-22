package com.example.android.fa3el5eer.notificationsSettings;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NotificationsSettings_api {

    @FormUrlEncoded
    @POST("notifications-settings")
    Call<NotificationsSettingsResponse>setNotificationsSettingsResonse(
            @Field("api_token") String api_token,
            @Field("cities[]") ArrayList<Integer> cities,
            @Field("blood_types[]") ArrayList<String> blood_types
            );
}
