package com.example.android.fa3el5eer.donationDetails;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.fa3el5eer.HomeActivity;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.donationRequestList.Datum;
import com.example.android.fa3el5eer.donationRequestList.Fragment2;
import com.example.android.fa3el5eer.register.Client;
import com.example.android.fa3el5eer.register.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView name,age,bloodType,bloodBags,hos_name,hos_address,phone,details;
    TextView nameTitle;
    ImageView back;
    private int id;
    private SharedPreferences preferences;
    private String api_token;
    private Button call_btn;
    private String[] permissions;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
         api_token = preferences.getString("api_token", "");

         call_btn = view.findViewById(R.id.ask_donate_call_btn);
        name = view.findViewById(R.id.details_donate_name);
        age = view.findViewById(R.id.details_donate_age);
        bloodType = view.findViewById(R.id.details_donate_blodeType);
        bloodBags = view.findViewById(R.id.details_donate_bags_numbers);
        hos_name = view.findViewById(R.id.hospital_name);
        hos_address = view.findViewById(R.id.hospital_address);
        phone = view.findViewById(R.id.details_donate_phone);
        details = view.findViewById(R.id.details_donate_notes);

        permissions = new String[]{ Manifest.permission.CALL_PHONE };
         call_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(isPermissionGranted()){
                     call_action();
                 }
             }
         });





        Bundle bundle = getArguments();

        if (bundle != null){

            id = bundle.getInt("requestModel");
//
//            Datum requestModel = bundle.getParcelable("requestModel");
//            name.setText(requestModel.getPatientName());
//            age.setText(requestModel.getPatientAge());
//            bloodType.setText(requestModel.getBloodType());
//            bloodBags.setText(requestModel.getBagsNum());
//            hos_name.setText(requestModel.getHospitalName());
//            hos_address.setText(requestModel.getHospitalAddress());
//            phone.setText(requestModel.getPhone());
//            details.setText(requestModel.getNotes());
        }

        viewData();
        return view;
    }
    private boolean isPermissionGranted() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }


    public void call_action(){
        String phone1 = phone.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone1));
        startActivity(callIntent);
    }

    public void viewData(){
        Retrofit retrofit = RetrofitClient.getInstant();
        DonationDetails_Api donationDetails_api = retrofit.create(DonationDetails_Api.class);
        Call<DonationDetailsResponse> donationDetailsResponse = donationDetails_api.getDonationDetailsResponse(api_token, id);
        donationDetailsResponse.enqueue(new Callback<DonationDetailsResponse>() {
            @Override
            public void onResponse(Call<DonationDetailsResponse> call, Response<DonationDetailsResponse> response) {
                if (response.body().getStatus() == 1){

                    com.example.android.fa3el5eer.donationDetails.Data data = response.body().getData();
                    phone.setText(data.getPhone());
                    name.setText(data.getPatientName());
                    age.setText(data.getPatientAge());
                    bloodBags.setText(data.getBagsNum());
                    bloodType.setText(data.getBloodType());
                    details.setText(data.getNotes());
                    hos_address.setText(data.getHospitalAddress());
                    hos_name.setText(data.getHospitalName());

                }
            }

            @Override
            public void onFailure(Call<DonationDetailsResponse> call, Throwable t) {

            }
        });

    }

}
