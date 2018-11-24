package com.example.android.fa3el5eer.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.fa3el5eer.R;
import com.example.android.fa3el5eer.donationRequestList.Datum;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Datum> requestModelList;
    private Context context;
    private onItemClickListner onItemClickedListner;


    public interface onItemClickListner {
        void onClick(Datum requestModel);//pass your object types.
    }

    public void onItemClickedListner(RequestAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickedListner = onItemClickListner;
    }


    public RequestAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, final int position) {
        Datum requestModel = requestModelList.get(position);

        holder.name.setText(requestModel.getPatientName());
        holder.hospital.setText(requestModel.getHospitalName());
        holder.city.setText(requestModel.getCity().getName());
        holder.blood.setText(requestModel.getBloodType());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datum datum = requestModelList.get(position);
                String phone = datum.getPhone();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(callIntent);
            }
        });

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datum datum = requestModelList.get(position);
                onItemClickedListner.onClick(datum);
            }
        });

    }

    @Override
    public int getItemCount() {
        return requestModelList!=null?requestModelList.size():0;
    }

    public void setTOAdapter(List<Datum> requestModelList) {
        this.requestModelList = requestModelList;
    }


    public class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView name,hospital,city,blood;
        Button details,call;
        public RequestViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameText);
            hospital = itemView.findViewById(R.id.hospital_name);
            city = itemView.findViewById(R.id.city_name);
            blood = itemView.findViewById(R.id.blood_no3);

            details = itemView.findViewById(R.id.details);
            call = itemView.findViewById(R.id.call);

//            details.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Datum requestModel = requestModelList.get(RequestAdapter.this.getItemViewType(getAdapterPosition()));
//
//                    onItemClickedListner.onClick(requestModel);
//
//                }
            //});
        }
    }
}
