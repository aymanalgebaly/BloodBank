package com.example.android.fa3el5eer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.donationDetails.DetailsFragment;
import com.example.android.fa3el5eer.favourites_articals.Datum;
import com.example.android.fa3el5eer.favourites_articals.FavoriteFragment;
import com.example.android.fa3el5eer.posts.Articals_info_Fragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritesArticalsAdapter extends RecyclerView.Adapter<FavouritesArticalsAdapter.FavouritesViewHolder>{
    private Context context;
    private List<Datum>datumList;
    private List<Datum> articalsList;


//    FavouritesArticalsAdapter.onItemClickListner onItemClickListner;
//
//    public void setOnItemClickListner(FavouritesArticalsAdapter.onItemClickListner onItemClickListner) {
//        this.onItemClickListner = onItemClickListner;
//    }
//
//
//    public interface onItemClickListner {
//        void onClick(com.example.android.fa3el5eer.posts.Datum articalsModel);//pass your object types.
//    }



    public FavouritesArticalsAdapter(Context context){
        this.context = context;
    }


    public void setFavouriteDataToAdapter(List<Datum> data) {

        this.datumList = data;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_articals,parent,false);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
//        final com.example.android.fa3el5eer.posts.Datum articalsModel = articalsList.get(position);

        Datum favouritesModel = datumList.get(position);

        holder.textView.setText(favouritesModel.getTitle());
        Picasso.get().load(favouritesModel.getThumbnail()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return datumList != null?datumList.size():0;
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView img1;
//        CheckBox artical_love;

        public FavouritesViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textArticals);
            img1 = itemView.findViewById(R.id.imgarticals2);
//            artical_love = itemView.findViewById(R.id.artical_love);

//            final Datum favouritesModel = datumList.get(FavouritesArticalsAdapter.this.getItemViewType(getAdapterPosition()));
            //final com.example.android.fa3el5eer.posts.Datum articalsModel = articalsList.get(FavouritesArticalsAdapter.this.getItemViewType(getAdapterPosition()));


//            if (artical_love.isChecked()){
//
//                Intent intent = new Intent(context,FavoriteFragment.class);
//                Bundle b = new Bundle();
//                b.putParcelable("favouritesModel", favouritesModel);
//                intent.putExtras(b);
//                context.startActivity(intent);
//            }
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //onItemClickListner.onClick(articalsModel);
//                    Intent i = new Intent(context, Articals_info_Fragment.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("favouritesModel",favouritesModel);
//                     i.putExtras(bundle);
//                    context.startActivity(i);
//                }
//            });
        }
    }
}
