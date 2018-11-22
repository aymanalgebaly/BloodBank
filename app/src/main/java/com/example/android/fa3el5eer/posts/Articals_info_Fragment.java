package com.example.android.fa3el5eer.posts;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.ArticalsAdapter;
import com.example.android.fa3el5eer.postsList.Datum;
import com.example.android.fa3el5eer.postsList.PostsListData;
import com.example.android.fa3el5eer.postsList.PostsListResponse;
import com.example.android.fa3el5eer.register.Client;
import com.example.android.fa3el5eer.register.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Articals_info_Fragment extends Fragment {

    TextView title,details;
    ImageView imageView;
    private int id;
    private ArticalsAdapter adapter;
    private SharedPreferences preferences;
    private String api_token;


    public Articals_info_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articals_info_, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        imageView = view.findViewById(R.id.artical_info_img);
        title = view.findViewById(R.id.artical_title_text);
        details = view.findViewById(R.id.artical_info_details);

        Bundle bundle = getArguments();
        if (bundle != null){

            id = bundle.getInt("articalsModel", id);
            com.example.android.fa3el5eer.postsList.Datum articalsModel = bundle.getParcelable("articalsModel");

        }
        viewPostsList();
        return view;
    }
    private void viewPostsList() {

        Retrofit retrofit = RetrofitClient.getInstant();
        PostsList_Api postsList_api = retrofit.create(PostsList_Api.class);
        Call<PostsDetailsResponse> postsListResponse = postsList_api.getPostsListResponse(api_token,id);
        postsListResponse.enqueue(new Callback<PostsDetailsResponse>() {
            @Override
            public void onResponse(Call<PostsDetailsResponse> call, Response<PostsDetailsResponse> response) {

                if (response.body().getStatus() == 1) {

                    com.example.android.fa3el5eer.posts.Data data = response.body().getData();


                    Picasso.get().load(data.getThumbnailFullPath()).into(imageView);
                    //viewResponsePostsList(body);
                    }
                }


            @Override
            public void onFailure(Call<PostsDetailsResponse> call, Throwable t) {

            }
        });
    }
//    private void viewResponsePostsList(PostsListResponse body) {
//        List<com.example.android.fa3el5eer.postsList.Datum> data = body.getPostsListData().getData();
//        adapter.setDataToAdapterPosts(data);
//        adapter.notifyDataSetChanged();
//    }
}
