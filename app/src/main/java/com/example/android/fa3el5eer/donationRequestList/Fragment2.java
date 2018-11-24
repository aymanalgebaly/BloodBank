package com.example.android.fa3el5eer.donationRequestList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.fa3el5eer.donationDetails.DetailsFragment;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.RequestAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private String api_token;
    private String blood_type = "A-";
    private String city_id = "1";
    private String url = "http://ipda3.com/blood-bank/api/v1/donation-requests?";
    private Button call_btn;


    private Button details;
    private SharedPreferences preferences;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        call_btn = view.findViewById(R.id.call);
        recyclerView = view.findViewById(R.id.rv2);
        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token",api_token);


        setupRecycler();
        fetchData();
        onTouchAdapter();

        return view;
    }

    private void fetchData() {

        Retrofit retrofit = RetrofitClient.getInstant();
        DonationRequestList_Api donationRequestList_api = retrofit.create(DonationRequestList_Api.class);
        Call<DonationListResponse> call = donationRequestList_api.getDonationListResponse(url,api_token);
        call.enqueue(new Callback<DonationListResponse>() {
            @Override
            public void onResponse(Call<DonationListResponse> call, Response<DonationListResponse> response) {
                if (response.body().getStatus() == 1){

                    for (int i = 0; i <response.body().getDonationData().getData().size() ; i++) {

                        DonationListResponse body = response.body();
                        viewData(body);

                    }
                }
            }

            @Override
            public void onFailure(Call<DonationListResponse> call, Throwable t) {

            }
        });


//        List<RequestModel> requestModelList = new ArrayList<>();
//        String name [] ={"احمد","محمد","اسلام","هاني"};
//        String hospital [] = {"الدولي","العام","السلاب","دار الفؤاد"};
//        String city [] = {"المنصوره","المنصوره","القاهره","الجيزه"};
//        String blood [] = {"B+","A","O-","AB"};
//        String addrss_hospital [] = {"المنصوره","القاهره","الجيزه","الشرقيه"};
//        String details [] = {"البتالنال","بعفبالن","اتلبفبعفب","غببلالع"};
//        String age [] = {"25","20","40","60"};
//        String bags [] = {"3","2","6","10"};
//        String phone_number [] = {"21516515","1564684","2156465","51654651"};
//
//        for (int i = 0; i <name.length ; i++) {
//            requestModelList.add(new RequestModel(name[i],hospital[i],city[i],blood[i],addrss_hospital[i],details[i]
//            ,age[i],bags[i],phone_number[i]));
//        }
//        adapter.setTOAdapter(requestModelList);
//        adapter.notifyDataSetChanged();
    }

    private void viewData(DonationListResponse body) {
        List<Datum> data = (List<Datum>) body.getDonationData().getData();
        adapter.setTOAdapter(data);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RequestAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }


    private void onTouchAdapter(){
        adapter.onItemClickedListner(new RequestAdapter.onItemClickListner() {
            @Override
            public void onClick(Datum requestModel) {
                Bundle bundle = new Bundle();
                Integer id = requestModel.getId();
                bundle.putInt("requestModel" , id);

                Fragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }
        });
    }

}
