
package com.example.android.fa3el5eer.reset_password;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordData {

    @SerializedName("pin_code_for_test")
    @Expose
    private Integer pinCodeForTest;

    public Integer getPinCodeForTest() {
        return pinCodeForTest;
    }

    public void setPinCodeForTest(Integer pinCodeForTest) {
        this.pinCodeForTest = pinCodeForTest;
    }

}
