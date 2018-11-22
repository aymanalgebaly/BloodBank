package com.example.android.fa3el5eer.postsList;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.ArticalsAdapter;
import com.example.android.fa3el5eer.posts.Articals_info_Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    private RecyclerView recyclerView;
    private ArticalsAdapter adapter;
    private Context context;
    private SharedPreferences preferences;
    private String api_token;

//    private String Posts_list = "http://ipda3.com/blood-bank/api/v1/post?" +
//            "api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27&post_id=1";
//    private String url = "http://ipda3.com/blood-bank/api/v1/posts?" +
//            "api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        preferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        recyclerView = view.findViewById(R.id.rv1);

        setupRecycler();
        ViewData();
        onTouchAdapter();

        return view;
    }




    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ArticalsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    public void ViewData() {
        Retrofit retrofit = RetrofitClient.getInstant();
        Posts_Api posts_api = retrofit.create(Posts_Api.class);
        Call<PostsListResponse> call = posts_api.getPostsResponse(api_token);
        call.enqueue(new Callback<PostsListResponse>() {
            @Override
            public void onResponse(Call<PostsListResponse> call, Response<PostsListResponse> response) {
                if (response.body().getStatus() == 1) {
                        PostsListResponse body = response.body();
                        viewResponse(body);
                }
            }

            @Override
            public void onFailure(Call<PostsListResponse> call, Throwable t) {

            }
        });

    }

    private void viewResponse(PostsListResponse body) {
        List<Datum> articalData = body.getPostsListData().getData();
        adapter.setDataToAdapter(articalData);
        adapter.notifyDataSetChanged();
    }

    private void onTouchAdapter() {
        adapter.setOnItemClickListner(new ArticalsAdapter.onItemClickListner() {

            @Override
            public void onClick(Datum articalsModel) {


                Bundle bundle = new Bundle();
                Integer id = articalsModel.getId();
                bundle.putInt("articalsModel",id);

                Fragment fragment = new Articals_info_Fragment();
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();


            }

        });


    }


}
