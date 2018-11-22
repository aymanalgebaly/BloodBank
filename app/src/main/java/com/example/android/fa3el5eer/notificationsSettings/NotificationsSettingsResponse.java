
package com.example.android.fa3el5eer.notificationsSettings;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsSettingsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private NotificationsSettingsData notificationsSettingsData;

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

    public NotificationsSettingsData getNotificationsSettingsData() {
        return notificationsSettingsData;
    }

    public void setData(NotificationsSettingsData notificationsSettingsData) {
        this.notificationsSettingsData = notificationsSettingsData;
    }

}
