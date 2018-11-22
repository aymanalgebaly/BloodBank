package com.example.android.fa3el5eer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.notificationsList.Datum;
import com.example.android.fa3el5eer.notificationsList.NotificationsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private Context context;
    private List<Datum> notificationsList;
    private onItemClickListner onItemClickedListner;

    public NotificationsAdapter(NotificationsActivity notificationsActivity) {

    }

    public void setToAdapter(Context context, List<Datum> data) {
        this.context = context;
        this.notificationsList = data;
    }

    public interface onItemClickListner {
        void onClick(Datum notificationsModel);//pass your object types.
    }

    public void onItemClickedListner(NotificationsAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickedListner = onItemClickListner;
    }


    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notifications_list, parent, false);
        return new NotificationsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        Datum notificationsModel = notificationsList.get(position);
        holder.title.setText(notificationsModel.getTitle());
        holder.date.setText(notificationsModel.getUpdatedAt());

        int r = notificationsModel.getPivot().getIsRead();
        if (r == 0) {
            Picasso.get().load(R.mipmap.notification).into(holder.alarm);

        } else {
            Picasso.get().load(R.mipmap.notification).into(holder.alarm);

        }


    }

    @Override
    public int getItemCount() {
        return notificationsList != null ? notificationsList.size() : 0;
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {

        TextView date, title;
        ImageView alarm;

        public NotificationsViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date_noti);
            title = itemView.findViewById(R.id.title_noti);
            alarm = itemView.findViewById(R.id.alarm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Datum notificationsModel = notificationsList.get(NotificationsAdapter.this.getItemViewType
                            (getAdapterPosition()));
                    onItemClickedListner.onClick(notificationsModel);

                }
            });
        }
    }
}
