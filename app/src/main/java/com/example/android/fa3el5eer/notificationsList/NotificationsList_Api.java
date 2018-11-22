package com.example.android.fa3el5eer.notificationsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NotificationsList_Api {
    @GET()
    Call<NotificationsListResponse> getNotificationsList(@Url String url);
}
