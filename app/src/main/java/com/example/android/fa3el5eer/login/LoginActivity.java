package com.example.android.fa3el5eer.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.fa3el5eer.HomeActivity;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.register.RegisterActivity;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.reset_password.ResetPasswordData;
import com.example.android.fa3el5eer.reset_password.ResetPasswordResponse;
import com.example.android.fa3el5eer.reset_password.ResetPassword_Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText mail,pass;
    private Button login,register;
    private CheckBox checkBox;
    private TextView textView;
    private String email,password;
    String api_token ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.textQues);
        checkBox = findViewById(R.id.checkBox);
        mail = findViewById(R.id.input_email);
        pass = findViewById(R.id.input_password);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewResetPassword();
            }
        });

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
//                Intent i = new Intent(LoginActivity.this,HomeActivity.class);
//                startActivity(i);
            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void viewResetPassword() {



        Retrofit retrofit = RetrofitClient.getInstant();
        ResetPassword_Api resetPassword_api = retrofit.create(ResetPassword_Api.class);
        Call<ResetPasswordResponse> resetPasswordResponse = resetPassword_api.getResetPasswordResponse(email);
        resetPasswordResponse.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.body().getStatus() == 1){

                    Integer pinCodeForTest = response.body().getData().getPinCodeForTest();
                    Toast.makeText(LoginActivity.this, pinCodeForTest, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

            }
        });
    }

    public void userLogin() {
        email = mail.getText().toString();
        password = pass.getText().toString();

        if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

            Retrofit retrofit = RetrofitClient.getInstant();
            Login_Api login_api = retrofit.create(Login_Api.class);
            Call<Login> Call = login_api.setLogin(email, password);
            Call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.body().getStatus() == 1) {

                        api_token = response.body().getData().getApiToken();
                        sharedLogin();

                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Email or Password not Right", Toast.LENGTH_SHORT).show();
        }


    }


    public void sharedLogin() {

        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();

        // preferences = getSharedPreferences("user",MODE_PRIVATE);

//        editor.putBoolean("login", true);

//        editor.putString("name", userName);
//        editor.putString("email", userMail);
//        editor.putString("birth_date", userBirth);
//        editor.putString("phone", userPhone);
//        editor.putString("last_donate", userLastDateDonation);
//        editor.putString("blode_type", userBlood);
        editor.putString("api_token", api_token);
        editor.apply();
    }


}
