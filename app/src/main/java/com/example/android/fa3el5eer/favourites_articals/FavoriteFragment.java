package com.example.android.fa3el5eer.favourites_articals;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.adapter.FavouritesArticalsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavouritesArticalsAdapter adapter;
    private ImageView imageView;
    private TextView title,details;
    private String url = "http://ipda3.com/blood-bank/api/v1/my-favourites?";
      private String api_token;
    private SharedPreferences preferences;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token =preferences.getString("api_token","");

        imageView = view.findViewById(R.id.artical_info_img);
        title = view.findViewById(R.id.artical_title_text);
        details = view.findViewById(R.id.artical_info_details);

        Bundle bundle = getArguments();
        if (bundle != null) {

            com.example.android.fa3el5eer.favourites_articals.Datum articalsModel = bundle.getParcelable("favouritesModel");
            Picasso.get().load(articalsModel.getThumbnail()).into(imageView);
            title.setText(articalsModel.getTitle());

            if (articalsModel.getContent() != null) {
                details.setText(articalsModel.getContent());
            }
        }

        recyclerView = view.findViewById(R.id.rv_favorite);
        setupRecycler();
        viewData();

        return view;
    }

    private void viewData() {
        Retrofit retrofit = RetrofitClient.getInstant();
        Favourites_api favourites_api = retrofit.create(Favourites_api.class);
        Call<FavouritesResponse> favouritesResponse = favourites_api.getFavouritesResponse(url,api_token);
        favouritesResponse.enqueue(new Callback<FavouritesResponse>() {
            @Override
            public void onResponse(Call<FavouritesResponse> call, Response<FavouritesResponse> response) {
                if (response.body().getStatus() == 1){
                    for (int i = 0; i <response.body().getFavouritesData().getData().size() ; i++) {
                        FavouritesResponse body = response.body();
                        viewDataResponse(body);

                    }
                }
            }

            @Override
            public void onFailure(Call<FavouritesResponse> call, Throwable t) {

            }
        });
    }

    private void viewDataResponse(FavouritesResponse body) {
        List<com.example.android.fa3el5eer.favourites_articals.Datum> data = body.getFavouritesData().getData();
        adapter.setFavouriteDataToAdapter(data);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FavouritesArticalsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }

}
