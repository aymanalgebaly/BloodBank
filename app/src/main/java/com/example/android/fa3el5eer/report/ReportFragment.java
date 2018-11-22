package com.example.android.fa3el5eer.report;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    private EditText details;
    private Button sendBtn;
    private String details_text;
    private SharedPreferences preferences;
    private String api_token;


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        details = view.findViewById(R.id.report_id);

        sendBtn = view.findViewById(R.id.send_report);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });


        return view;
    }

    private void sendData() {

        details_text = details.getText().toString();
        if (TextUtils.isEmpty(details_text)){
            details.setError("details is required");
        }else {

            Retrofit retrofit = RetrofitClient.getInstant();
            Report_api report_api = retrofit.create(Report_api.class);
            Call<ReportResponse> reportResponseCall = report_api.setReportResponse(api_token, details_text);
            reportResponseCall.enqueue(new Callback<ReportResponse>() {
                @Override
                public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                    if (response.isSuccessful()){
                        Log.i( "reportResponse: ",response.body().getReportData().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ReportResponse> call, Throwable t) {
                    Log.i( "FreportResponse: ",t.getMessage());

                }
            });
        }



    }

}
