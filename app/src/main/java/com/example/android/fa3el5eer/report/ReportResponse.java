
package com.example.android.fa3el5eer.report;

import com.example.android.fa3el5eer.register.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ReportData reportData;

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

    public ReportData getReportData() {
        return reportData;
    }

    public void setData(ReportData reportData) {
        this.reportData = reportData;
    }

}
