package com.example.android.fa3el5eer.notificationsList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.NotificationsAdapter;
import com.example.android.fa3el5eer.notifications_count.NotificationsCountResponse;
import com.example.android.fa3el5eer.notifications_count.NotificationsCount_Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotificationsAdapter notificationsAdapter;
    private Context context;
    private String url = "http://ipda3.com/blood-bank/api/v1/notifications?" +
            "api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = findViewById(R.id.rv_notification);

        setupRecycler();
        viewDataResponse();


    }

    private void viewDataResponse() {
        Retrofit retrofit = RetrofitClient.getInstant();
        NotificationsList_Api notificationsList_api = retrofit.create(NotificationsList_Api.class);
        Call<NotificationsListResponse> notificationsList = notificationsList_api.getNotificationsList(url);
        notificationsList.enqueue(new Callback<NotificationsListResponse>() {
            @Override
            public void onResponse(Call<NotificationsListResponse> call, Response<NotificationsListResponse> response) {
                if (response.body().getStatus() == 1){
//                    for (int i = 0; i <response.body().getNotificationsListData().getData().size() ; i++) {

                        NotificationsListResponse body = response.body();
                        viewData(body);

                    }
                }
//            }

            @Override
            public void onFailure(Call<NotificationsListResponse> call, Throwable t) {

            }
        });

    }

    private void viewData(NotificationsListResponse body) {
        List<Datum> data = body.getNotificationsListData().getData();
        notificationsAdapter.setToAdapter(NotificationsActivity.this,data);
        notificationsAdapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        notificationsAdapter = new NotificationsAdapter(this);
        recyclerView.setAdapter(notificationsAdapter);
    }
}
