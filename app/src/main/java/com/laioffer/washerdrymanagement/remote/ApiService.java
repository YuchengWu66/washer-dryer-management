package com.laioffer.washerdrymanagement.remote;

import com.laioffer.washerdrymanagement.remote.response.RemoteResponse;
import com.laioffer.washerdrymanagement.remote.response.UserInfo;
import com.laioffer.washerdrymanagement.ui.login.LoginEvent;
import com.laioffer.washerdrymanagement.ui.register.RegisterEvent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Call<UserInfo> login(@Body LoginEvent body);

    @POST("register")
    Call<RemoteResponse<UserInfo>> register(@Body RegisterEvent body);

    @GET("/path/to/get")
    Call<Void> getMyData(/* your args here */);

}
