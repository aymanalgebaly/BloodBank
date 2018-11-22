package com.example.android.fa3el5eer.donationDetails;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.HomeActivity;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.donationRequestList.Datum;
import com.example.android.fa3el5eer.donationRequestList.Fragment2;
import com.example.android.fa3el5eer.register.Client;
import com.example.android.fa3el5eer.register.Data;

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

        back = view.findViewById(R.id.backImg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),HomeActivity.class);
                startActivity(i);
            }
        });

        name = view.findViewById(R.id.input_textName);
        age = view.findViewById(R.id.input_textAge);
        bloodType = view.findViewById(R.id.input_textBlood);
        bloodBags = view.findViewById(R.id.input_textBags);
        hos_name = view.findViewById(R.id.input_textHospital);
        hos_address = view.findViewById(R.id.input_textAddressHos);
        phone = view.findViewById(R.id.input_textPhone);
        details = view.findViewById(R.id.input_textDetails);

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
