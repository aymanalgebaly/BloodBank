
package com.example.android.fa3el5eer.notificationsList;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsListResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private NotificationsListData notificationsListData;

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

    public NotificationsListData getNotificationsListData() {
        return notificationsListData;
    }

    public void setData(NotificationsListData notificationsListData) {
        this.notificationsListData = notificationsListData;
    }

}
