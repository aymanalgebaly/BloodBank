package com.example.android.fa3el5eer.notifications_count;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NotificationsCount_Api {

    @GET()
    Call<NotificationsCountResponse> getNotificationsCountResponse(@Url String url);
}
