
package com.example.android.fa3el5eer.notificationsList;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("is_read")
    @Expose
    private Integer isRead;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

}
