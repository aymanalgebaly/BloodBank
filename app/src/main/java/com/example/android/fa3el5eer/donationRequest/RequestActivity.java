package com.example.android.fa3el5eer.donationRequest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.fa3el5eer.HomeActivity;
import com.example.android.fa3el5eer.cities.CitiesResponse;
import com.example.android.fa3el5eer.cities.Cities_Api;
import com.example.android.fa3el5eer.donationRequestList.Fragment2;
import com.example.android.fa3el5eer.gavernorates.Datum;
import com.example.android.fa3el5eer.gavernorates.GavernoratesResponse;
import com.example.android.fa3el5eer.gavernorates.Gavernorates_Api;
import com.example.android.fa3el5eer.notificationsList.NotificationsActivity;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.register.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestActivity extends AppCompatActivity {

    private ImageView back,noti;
    private Button send_request;
    private EditText name,age,blood_type,blood_bags,phone,nameOfHospital,addressOfHospital,notes;
    private Spinner spinner_city,spinner_gov;
    private String req_name,req_age,req_blood_type,req_blood_bags,req_phone,req_hos_name,req_hos_address,req_notes;
    private String api_token ;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        preferences = getSharedPreferences("user",MODE_PRIVATE);
        api_token = preferences.getString("api_token","");

        name = findViewById(R.id.input_name);
        age = findViewById(R.id.input_age);
        blood_type = findViewById(R.id.input_blood_type);
        blood_bags = findViewById(R.id.input_blood_bags);
        phone = findViewById(R.id.input_phoneNum);
        nameOfHospital = findViewById(R.id.input_name_hos);
        addressOfHospital = findViewById(R.id.input_address_hos);
        notes = findViewById(R.id.input_notes);
        spinner_city = findViewById(R.id.custom_spinner_2);
        spinner_gov = findViewById(R.id.custom_spinner);



        send_request = findViewById(R.id.send_request);
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createRequest();
                Intent intent = new Intent(RequestActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.backImg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestActivity.this,NotificationsActivity.class);
                startActivity(i);
            }
        });

        noti = findViewById(R.id.notificationImg);
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestActivity.this,NotificationsActivity.class);
                startActivity(intent);
            }
        });

        viewCities();
        viewGovernorates();
    }

    private void viewCities() {

        Retrofit retrofit = RetrofitClient.getInstant();
        Cities_Api cities_api = retrofit.create(Cities_Api.class);
        Call<CitiesResponse> citiesResponse = cities_api.getCitiesResponse(1);
        citiesResponse.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {

                if (response.body().getStatus() == 1) {
                    CitiesResponse body = response.body();
                    viewCitiesResonse(body);
                }
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

            }
        });

    }

    private void viewCitiesResonse(CitiesResponse body) {
        List<com.example.android.fa3el5eer.cities.Datum> data = body.getData();
        ArrayAdapter city = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
        city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(city);
    }

    private void viewGovernorates() {
        Retrofit retrofit = RetrofitClient.getInstant();
        Gavernorates_Api gavernorates_api = retrofit.create(Gavernorates_Api.class);
        Call<GavernoratesResponse> gavernoratesResponse = gavernorates_api.getGavernoratesResponse();
        gavernoratesResponse.enqueue(new Callback<GavernoratesResponse>() {
            @Override
            public void onResponse(Call<GavernoratesResponse> call, Response<GavernoratesResponse> response) {

                if (response.body().getStatus() == 1) {
                    GavernoratesResponse body = response.body();
                    viewGovernoratesResponse(body);
                }
            }

            @Override
            public void onFailure(Call<GavernoratesResponse> call, Throwable t) {

            }
        });
    }

    private void viewGovernoratesResponse(GavernoratesResponse body) {

        List<Datum> data = body.getData();
        ArrayAdapter gov = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
        gov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gov.setAdapter(gov);
    }

    private void createRequest() {

        req_name = name.getText().toString();
        req_age = age.getText().toString();
        req_blood_type = blood_type.getText().toString();
        req_blood_bags = blood_bags.getText().toString();
        req_phone = phone.getText().toString();
        req_hos_name = nameOfHospital.getText().toString();
        req_hos_address = addressOfHospital.getText().toString();
        req_notes = notes.getText().toString();


        SharedPreferences.Editor editor = getSharedPreferences("user",MODE_PRIVATE).edit();
        editor.putString("name",req_name);
        editor.putString("age",req_age);
        editor.putString("blood_type",req_blood_type);
        editor.putString("blood_bags",req_blood_bags);
        editor.putString("phone",req_phone);
        editor.putString("hoss_name",req_hos_name);
        editor.putString("hoss_address",req_hos_address);
        editor.putString("notes",req_notes);
        editor.apply();

        if (TextUtils.isEmpty(req_name)){
            name.setError("name is required");
        }else if (TextUtils.isEmpty(req_age)){
            age.setError("age is required");
        }else if (TextUtils.isEmpty(req_blood_type)){
            blood_type.setError("blood type is required");
        }else if (TextUtils.isEmpty(req_blood_bags)){
            blood_bags.setError("number of blood bags is required");
        }else if (TextUtils.isEmpty(req_phone)){
            phone.setError("phone number is required");
        }else if (TextUtils.isEmpty(req_hos_name)){
            nameOfHospital.setError("name of the hospital is required");
        }else if (TextUtils.isEmpty(req_hos_address)){
            addressOfHospital.setError("address of the hospital is required");
        }else{

            Retrofit retrofit = RetrofitClient.getInstant();
            DonationRequest_Api donationRequest_api = retrofit.create(DonationRequest_Api.class);
            Call<DonationRequestResponse> donationRequestResponse =
                    donationRequest_api.getDonationRequestResponse(
                            api_token,
                            req_name, req_age, req_blood_type, req_blood_bags,
                    req_hos_name, req_hos_address, "1", req_phone,
                            req_notes);
            donationRequestResponse.enqueue(new Callback<DonationRequestResponse>() {
                @Override
                public void onResponse(Call<DonationRequestResponse> call, Response<DonationRequestResponse> response) {

                    if (response.body().getStatus() == 1){
//                        DonationRequestResponse body = response.body();
//                        viewRequestResponse(body);
                        Toast.makeText(RequestActivity.this, "sent", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onFailure(Call<DonationRequestResponse> call, Throwable t) {

                    Toast.makeText(RequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

//    private void viewRequestResponse(DonationRequestResponse body) {
//
//        Data data = body.getData();
//
//    }
}
