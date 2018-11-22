
package com.example.android.fa3el5eer.donationDetails;

import com.example.android.fa3el5eer.donationDetails.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationDetailsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private com.example.android.fa3el5eer.donationDetails.Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public com.example.android.fa3el5eer.donationDetails.Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
