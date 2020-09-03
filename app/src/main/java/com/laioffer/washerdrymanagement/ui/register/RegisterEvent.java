package com.laioffer.washerdrymanagement.ui.register;

import com.google.gson.annotations.SerializedName;

public class RegisterEvent {
    @SerializedName("user_id")
    final String userId;
    @SerializedName("password")
    final String password;
    @SerializedName("email")
    final String email;

    RegisterEvent(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
