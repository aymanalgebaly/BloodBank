package com.example.android.fa3el5eer.settingsFace;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactWithUsFragment extends Fragment {

    private TextView phoneNum,mail;
    private ImageView face,twitter,google,whats,youtube,instagram;
    private String url ;
    private SharedPreferences preferences;
    private Button callBtn,sendBtn;
    private String number,titleName,nameUser,emailTitle,phoneNumber,detailsTitle;
    private String api_token;
    private EditText title,name,email,phone,details;
    private String email1,phone2,facebookUrl,twitterUrl,youtubeUrl,instagramUrl,whatsapp,googleUrl;






    public ContactWithUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_with_us, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        phoneNum = view.findViewById(R.id.text_number);
        mail = view.findViewById(R.id.mail_app);
        face = view.findViewById(R.id.facebook);
        twitter = view.findViewById(R.id.twitter);
        google = view.findViewById(R.id.google);
        whats = view.findViewById(R.id.whats);
        youtube = view.findViewById(R.id.youtube);
        instagram = view.findViewById(R.id.inista);
        callBtn = view.findViewById(R.id.call_us);


        title = view.findViewById(R.id.edit_subject);
        name = view.findViewById(R.id.edit_name);
        email = view.findViewById(R.id.edit_mail);
        phone = view.findViewById(R.id.edit_phone);
        details = view.findViewById(R.id.edit_text_subject);
        sendBtn = view.findViewById(R.id.send);


        getResponse();


        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
              startActivity(browserIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                startActivity(browserIntent1);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl));
                startActivity(browserIntent2);
            }
        });
        whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(whatsapp));
//                startActivity(browserIntent3);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                startActivity(browserIntent4);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
                startActivity(browserIntent5);
            }
        });


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = phoneNum.getText().toString();


                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            101);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
                }
            }
        });




        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();

                 }
        });



        return view;
    }

    private void getResponse() {


        Retrofit retrofit = RetrofitClient.getInstant();
        ContactUs_Api contactUs_api = retrofit.create(ContactUs_Api.class);
        Call<ContactUsResponse> contactUsResponse = contactUs_api.getContactUsResponse(api_token);
        contactUsResponse.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, final Response<ContactUsResponse> response) {
                if (response.body().getStatus() == 1){

                    ContactUsData contactUsData = response.body().getContactUsData();
                    email1      = contactUsData.getEmail();
                    phone2      = contactUsData.getPhone();
                    facebookUrl = contactUsData.getFacebookUrl();
                    googleUrl    = contactUsData.getGoogleUrl();
                    instagramUrl = contactUsData.getInstagramUrl();
                    twitterUrl   = contactUsData.getTwitterUrl();
                    youtubeUrl   = contactUsData.getYoutubeUrl();
                    whatsapp    = contactUsData.getWhatsapp();

                    mail.setText(email1);
                    phoneNum.setText(phone2);
                }
            }

            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

            }
        });

    }

    private void sendData() {
        titleName = title.getText().toString();
        nameUser = name.getText().toString();
        emailTitle = email.getText().toString();
        phoneNumber = phone.getText().toString();
        detailsTitle = details.getText().toString();

        nameUser = preferences.getString("name", "");
        emailTitle = preferences.getString("email","");
        phoneNumber = preferences.getString("phone","");

        if (TextUtils.isEmpty(nameUser)){
            this.name.setError("name is required");
        }else if (TextUtils.isEmpty(emailTitle)){
            email.setError("email is required");
        }else if (TextUtils.isEmpty(phoneNumber)){
            phone.setError("phone number is required");
        }else if (TextUtils.isEmpty(titleName)){
            title.setError("title is required");
        }else if (TextUtils.isEmpty(detailsTitle)){
            details.setError("details is requied");
        }else {
            sendResponse();
        }
    }

    private void sendResponse() {

        Retrofit retrofit1 = RetrofitClient.getInstant();
        ContactUs_Api contactUs_api1 = retrofit1.create(ContactUs_Api.class);
        Call<ContactWithUsResponse> contactWithUsResponseCall = contactUs_api1.setSettingsContact(api_token, titleName, detailsTitle);
        contactWithUsResponseCall.enqueue(new Callback<ContactWithUsResponse>() {
            @Override
            public void onResponse(Call<ContactWithUsResponse> call, Response<ContactWithUsResponse> response) {
                if (response.isSuccessful()){

                    Log.i("contactMsg" , response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ContactWithUsResponse> call, Throwable t) {

                Log.i("FcontactMsg" , t.getMessage());
            }
        });

    }

}
