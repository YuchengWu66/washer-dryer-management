package com.laioffer.washerdrymanagement.ui.register;

import com.google.gson.annotations.SerializedName;

public class RegisterEvent {
    @SerializedName("user_id")
    final String userId;
    @SerializedName("password")
    final String password;
    @SerializedName("phone_number")
    final String phoneNumber;

    RegisterEvent(String userId, String password, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
