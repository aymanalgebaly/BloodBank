package com.example.android.fa3el5eer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.notificationsList.Datum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NotificationsSettingsAdapter extends RecyclerView.Adapter<NotificationsSettingsAdapter.ViewHolderNotificationsSettings> {
    private Context context;
    private onItemClickListner onItemClickedListner;
    private List<com.example.android.fa3el5eer.cities.Datum>datumListCity;
    private List<com.example.android.fa3el5eer.cities.Datum> data;
    public ArrayList<Integer> Ids;

    public NotificationsSettingsAdapter(List<com.example.android.fa3el5eer.cities.Datum> datumListCity) {
        this.datumListCity = datumListCity;
    }

    public NotificationsSettingsAdapter(Context context) {
        this.context = context;
        Ids = new ArrayList<>();
    }

    public void sendDataToAdapter(List<com.example.android.fa3el5eer.cities.Datum> data) {
        this.datumListCity = data;
        this.data = data;
    }

    public interface onItemClickListner {
        void onClick(com.example.android.fa3el5eer.cities.Datum cityModel);//pass your object types.
        ArrayList<Integer> Ids = new ArrayList<>();
    }

    public void onItemClickedListner(NotificationsSettingsAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickedListner = onItemClickListner;
    }

    @NonNull
    @Override
    public ViewHolderNotificationsSettings onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notifications_settings_layout, parent, false);
        return new ViewHolderNotificationsSettings(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNotificationsSettings holder, final int position) {

        final com.example.android.fa3el5eer.cities.Datum datum = datumListCity.get(position);
        holder.checkBox.setText(datum.getName());


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ids.add(datumListCity.get(position).getId());
                }else {
                    for (int i = 0 ; i < Ids.size() ; i++){
                        if (Ids.get(i).equals(datumListCity.get(position).getId())) {
                            Ids.remove(i);
                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datumListCity != null?datumListCity.size():0;
    }

    public class ViewHolderNotificationsSettings extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        public ViewHolderNotificationsSettings(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.mansoura);

        }
    }
}
