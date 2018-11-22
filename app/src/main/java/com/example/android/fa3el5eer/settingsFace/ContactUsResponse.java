
package com.example.android.fa3el5eer.settingsFace;

import android.telecom.Call;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ContactUsData contactUsData;

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

    public ContactUsData getContactUsData() {
        return contactUsData;
    }

    public void setData(ContactUsData contactUsData) {
        this.contactUsData = contactUsData;
    }

}
