package com.example.android.fa3el5eer.notificationsSettings;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.android.fa3el5eer.HomeFragment;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.NotificationsSettingsAdapter;
import com.example.android.fa3el5eer.cities.CitiesResponse;
import com.example.android.fa3el5eer.cities.Cities_Api;
import com.example.android.fa3el5eer.cities.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSettingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button save;
    private SharedPreferences preferences;
    private CheckBox check_a, check_b, check_c, check_e, check_d, check_f;
    private String api_token;
    private NotificationsSettingsAdapter adapter;
    private ArrayList<Integer> Ids;
    private ArrayList<String>blood_typesList;


    public NotificationSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_settings, container, false);

        Ids = new ArrayList<>();
        blood_typesList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.rv_notificationsSettings);
        save = view.findViewById(R.id.save);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");
        Log.i("aapppd",api_token);

        check_a = view.findViewById(R.id.o_p);
        check_b = view.findViewById(R.id.ap_n);
        check_c = view.findViewById(R.id.a_n);
        check_d = view.findViewById(R.id.ap_p);
        check_e = view.findViewById(R.id.a_p);
        check_f = view.findViewById(R.id.o_n);



        CheckedChangeListener(check_a);
        CheckedChangeListener(check_b);
        CheckedChangeListener(check_c);
        CheckedChangeListener(check_d);
        CheckedChangeListener(check_e);
        CheckedChangeListener(check_f);


//        adapter.onItemClickedListner(new NotificationsSettingsAdapter.onItemClickListner() {
//            @Override
//            public void onClick(Datum cityModel) {
//                Integer id = cityModel.getId();
//                Ids.add(id);
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ids = adapter.Ids;

                viewNotifications();

                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setupRecycler();
        viewCities();
        return view;
    }

    private void CheckedChangeListener(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    blood_typesList.add(checkBox.getText().toString());

                }else {
                    for (int i = 0; i <blood_typesList.size() ; i++) {
                        if (blood_typesList.get(i).equals(checkBox.getText().toString())){
                            blood_typesList.remove(i);
                        }
                    }
                }
            }
        });
    }


    private void viewNotifications() {

        Retrofit retrofit = RetrofitClient.getInstant();
        NotificationsSettings_api notificationsSettings_api = retrofit.create(NotificationsSettings_api.class);
        Call<NotificationsSettingsResponse> notificationsSettingsResponseCall =
                notificationsSettings_api.setNotificationsSettingsResonse(api_token, Ids, blood_typesList);
        notificationsSettingsResponseCall.enqueue(new Callback<NotificationsSettingsResponse>() {
            @Override
            public void onResponse(Call<NotificationsSettingsResponse> call, Response<NotificationsSettingsResponse> response) {
                if (response.body().getStatus() == 1){
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationsSettingsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotificationsSettingsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void viewCities() {

        Retrofit retrofit = RetrofitClient.getInstant();
        Cities_Api cities_api = retrofit.create(Cities_Api.class);
        Call<CitiesResponse> citiesResponse = cities_api.getAlltCitiesResponse();
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
        List<Datum> data = body.getData();
        adapter.sendDataToAdapter(data);
        adapter.notifyDataSetChanged();
    }

}
