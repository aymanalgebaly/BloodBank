
package com.example.android.fa3el5eer.settingsFace;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactWithUsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ContactWithUsData contactWithUsData;

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

    public ContactWithUsData getContactWithUsData() {
        return contactWithUsData;
    }

    public void setData(ContactWithUsData contactWithUsData) {
        this.contactWithUsData = contactWithUsData;
    }

}
