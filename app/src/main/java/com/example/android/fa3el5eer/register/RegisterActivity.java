package com.example.android.fa3el5eer.register;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.fa3el5eer.HomeActivity;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.SpinnerAdapter;
import com.example.android.fa3el5eer.cities.CitiesResponse;
import com.example.android.fa3el5eer.cities.Cities_Api;
import com.example.android.fa3el5eer.gavernorates.Datum;
import com.example.android.fa3el5eer.gavernorates.GavernoratesResponse;
import com.example.android.fa3el5eer.gavernorates.Gavernorates_Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Boolean mAllowSelectionFiring = false;
    private Button button;
    private EditText name, email, blood, phone, pass, passConfirm;
    private TextView birth, lastDateDonation;
    private Spinner spinner_city, spinner_gov;
    private String userName, userMail, userBirth, userBlood, userPhone, userPass, userPassConfirm, userLastDateDonation;
    private SharedPreferences preferences;
    public String api_token;
    private int governrate_id;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = findViewById(R.id.input_name);
        email = findViewById(R.id.input_mail);
        birth = findViewById(R.id.input_birthday);
        blood = findViewById(R.id.input_blood);
        phone = findViewById(R.id.input_phoneNum);
        pass = findViewById(R.id.input_password_regis);
        passConfirm = findViewById(R.id.input_confirm_password);
        lastDateDonation = findViewById(R.id.input_lastDate);
        spinner_gov = findViewById(R.id.custom_spinner_gov);
        spinner_gov.setOnItemSelectedListener(this);
        spinner_city = findViewById(R.id.custom_spinner_city);
        button = findViewById(R.id.confirm_register);


        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };

        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        final DatePickerDialog.OnDateSetListener donationDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedonationDate();
            }

        };


        lastDateDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, donationDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        ArrayList<String> spinnerAdapterData = new ArrayList<>();
//        String[] spinnerItemsArray = getResources().getStringArray(R.array.governorate);
//        Collections.addAll(spinnerAdapterData, spinnerItemsArray);
//        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.layout_spinner, spinnerAdapterData, getResources());
//
//         itemList = (Spinner) findViewById(R.id.custom_spinner);
//        itemList.setAdapter(adapter);
//
//        itemList.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUser();
            }
        });


        viewGovernorates();
    }

    private void updateDate() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birth.setText(sdf.format(myCalendar.getTime()));

    }

    private void updatedonationDate() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        lastDateDonation.setText(sdf.format(myCalendar.getTime()));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        governrate_id = spinner_gov.getSelectedItemPosition() + 1;
        Log.i("gov_id", String.valueOf(governrate_id));
        viewCities();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    private void viewCities() {

        Retrofit retrofit = RetrofitClient.getInstant();
        Cities_Api cities_api = retrofit.create(Cities_Api.class);
        Call<CitiesResponse> citiesResponse = cities_api.getCitiesResponse(governrate_id);
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

    public void CreateUser() {

        userName = name.getText().toString();
        userMail = email.getText().toString();
        userBirth = birth.getText().toString();
        userBlood = blood.getText().toString();
        userPhone = phone.getText().toString();
        userPass = pass.getText().toString();
        userPassConfirm = passConfirm.getText().toString();
        userLastDateDonation = lastDateDonation.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            name.setError("User name is required");
        } else if (TextUtils.isEmpty(userMail)) {
            email.setError("Email is required");
        } else if (TextUtils.isEmpty(userBirth)) {
            birth.setError("Birthday is required");
        } else if (TextUtils.isEmpty(userBlood)) {
            blood.setError("Blood Type is required");
        } else if (TextUtils.isEmpty(userPhone)) {
            phone.setError("Phone Number is required");
        } else if (TextUtils.isEmpty(userPass)) {
            pass.setError("Password is required");
        } else if (!TextUtils.equals(userPass, userPassConfirm)) {
            passConfirm.setError("Password not matching");
        } else if (TextUtils.isEmpty(userLastDateDonation)) {
            lastDateDonation.setError("Last Donation is required");
        } else {


            Retrofit retrofit = RetrofitClient.getInstant();
            Register_Api register_api = retrofit.create(Register_Api.class);
            Call<RegisterResponse> call = register_api.setRegister(userName, userMail, userBirth, "1", userPhone, userPass,
                    userPassConfirm, userLastDateDonation, userBlood);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.body().getStatus() == 1) {

                        api_token = response.body().getData().getApiToken();

                        sharedLogin();

                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);


                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void sharedLogin() {

        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();

        preferences = getSharedPreferences("user", MODE_PRIVATE);

        editor.putBoolean("login", true);

        editor.putString("name", userName);
        editor.putString("email", userMail);
        editor.putString("birth_date", userBirth);
        editor.putString("phone", userPhone);
        editor.putString("last_donate", userLastDateDonation);
        editor.putString("blode_type", userBlood);
        editor.putString("api_token", api_token);
        editor.apply();
    }


}
