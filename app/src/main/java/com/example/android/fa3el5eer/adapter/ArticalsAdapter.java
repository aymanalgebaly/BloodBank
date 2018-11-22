package com.example.android.fa3el5eer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.RetrofitClient;
import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.favourites_articals.Favourites_api;
import com.example.android.fa3el5eer.favourites_articals.ToggleFavouritesResponse;
import com.example.android.fa3el5eer.postsList.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class ArticalsAdapter extends RecyclerView.Adapter<ArticalsAdapter.ArticalsViewHolder> {

    private final Context context;
    private List<Datum> articalsList;
    private List<com.example.android.fa3el5eer.postsList.Datum> postsList;
    private List<com.example.android.fa3el5eer.favourites_articals.Datum> favouritesList;
    private SharedPreferences preferences;

    onItemClickListner onItemClickListner;

    public void setOnItemClickListner(ArticalsAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setDataToAdapterPosts(List<com.example.android.fa3el5eer.postsList.Datum> data) {
        this.postsList = data;
    }


    public interface onItemClickListner {
        void onClick(Datum articalsModel);//pass your object types.
    }


    public void setDataToAdapter(List<Datum> articalssList) {
        this.articalsList = articalssList;
    }


    public ArticalsAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ArticalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_articals, parent, false);
        return new ArticalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticalsViewHolder holder, final int position) {

        final Datum articalsModel = articalsList.get(position);

        holder.textView.setText(articalsModel.getTitle());
        Picasso.get().load("http://ipda3.com/blood-bank"+articalsModel.getThumbnail()).into(holder.img1);

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datum datum = articalsList.get(position);
                onItemClickListner.onClick(datum);
            }
        });

        holder.artical_love.setChecked(articalsModel.getisFavourite());



        holder.artical_love.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (holder.artical_love.isChecked()) {
                    articalsModel.setIsFavourite(true);
                    boolean b = addToFavourite(articalsList.get(position).getId());
                    if (b) {
                        articalsList.get(position).setIsFavourite(true);
                        notifyDataSetChanged();

                    }
                } else {

                    articalsModel.setIsFavourite(false);
                    boolean b = addToFavourite(articalsList.get(position).getId());
                    if (b) {
                        articalsList.get(position).setIsFavourite(false);
                        notifyDataSetChanged();
                    }

                }

            }
        });

//        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ReportDetails.class);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return articalsList != null ? articalsList.size() : 0;
    }


    public class ArticalsViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView img1;
        CheckBox artical_love;

        public ArticalsViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textArticals);
            img1 = itemView.findViewById(R.id.imgarticals2);
            artical_love = itemView.findViewById(R.id.artical_love);

            final Datum articalsModel = articalsList.get(ArticalsAdapter.this.getItemViewType(getAdapterPosition()));




//            artical_love.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    if (artical_love.isChecked()) {
//                        artical_love.setChecked(true);
//
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable("articalsModel", articalsModel);
//
//
//                        HomeActivity homeActivity = new HomeActivity();
//                        Fragment fragment = new FavoriteFragment();
//                        fragment.setArguments(bundle);
//
//                    } else {
//                        artical_love.setChecked(false);
//                    }
//
//                }
//
//
//            });
        }
    }


    public Boolean addToFavourite(int id) {
        final boolean[] isChecked = {false};

        preferences = context.getSharedPreferences("user", MODE_PRIVATE);

        String api_token = preferences.getString("api_token", "");

        Log.i(TAG, api_token);

        Retrofit retrofit = RetrofitClient.getInstant();
        Favourites_api favourites_api = retrofit.create(Favourites_api.class);

        Call<ToggleFavouritesResponse> call = favourites_api.getToggleFavouritesResponse(id, api_token);
        call.enqueue(new Callback<ToggleFavouritesResponse>() {
            @Override
            public void onResponse(Call<ToggleFavouritesResponse> call, Response<ToggleFavouritesResponse> response) {


                if (response.body().getStatus() == 1) {
                    isChecked[0] = true;

                } else {
                    isChecked[0] = false;

                }

//                notifyDataSetChanged();

            }


            @Override
            public void onFailure(Call<ToggleFavouritesResponse> call, Throwable t) {
            }
        });


        return isChecked[0];
    }

}

