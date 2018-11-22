package com.example.android.fa3el5eer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.fa3el5eer.settingsFace.ContactUsResponse;
import com.example.android.fa3el5eer.settingsFace.ContactUs_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends Fragment {

    ImageView back;
    private String url = "http://ipda3.com/blood-bank/api/v1/settings?";
    private SharedPreferences preferences;
    private TextView text;


    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String api_token = preferences.getString("api_token", "");

        text = view.findViewById(R.id.about_app_text);

        back = view.findViewById(R.id.back_aboutApp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);

            }
        });

        Retrofit retrofit = RetrofitClient.getInstant();
        ContactUs_Api contactUs_api = retrofit.create(ContactUs_Api.class);
        Call<ContactUsResponse> contactUsResponse = contactUs_api.getContactUsResponse(url,api_token);
        contactUsResponse.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {

                if (response.body().getStatus() ==1){

                    String aboutApp = response.body().getContactUsData().getAboutApp().toString();
                    text.setText(aboutApp);
                }

            }

            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

            }
        });

        return view;
    }

}
