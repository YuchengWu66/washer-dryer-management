package com.laioffer.washerdrymanagement.ui.login;

import android.content.Context;
import android.util.Log;
import android.webkit.WebSettings;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.washerdrymanagement.Network.BackendClient;
import com.laioffer.washerdrymanagement.base.BaseRepository;
import com.laioffer.washerdrymanagement.remote.response.RemoteResponse;
import com.laioffer.washerdrymanagement.remote.response.UserInfo;
//import com.laioffer.washerdrymanagement.remote.response.UserProfile;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginRepository extends BaseRepository {
    public static Context context;
    public MutableLiveData<UserInfo> login(LoginEvent loginEvent) {
        MutableLiveData<UserInfo> responseMutableLiveData = new MutableLiveData<>();
        if (loginEvent == null) {
            return responseMutableLiveData;
        }
        Call<UserInfo> call = apiService.login(loginEvent);
        call.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", WebSettings.getDefaultUserAgent(context)).
                removeHeader("Connection").addHeader("Connection", "keep-alive").
                removeHeader("Accept").addHeader("Accept", "*/*")
                .removeHeader("Cookie").addHeader("Cookie", "JSESSIONID=4705BF1940DCE3C6CB5B3C665908514A");
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    responseMutableLiveData.setValue(response.body());
                    okhttp3.Response temp = response.raw();
                    Headers headers = temp.headers();
                    String v = headers.get("Set-Cookie");
                    String[] array = v.split(":*;");
                    BackendClient.Cookie = array[0];
                    Log.d("aaaa", "Cookie" + v);
                }
                else
                    responseMutableLiveData.setValue(null);
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d("aaaa", t.getMessage());
                responseMutableLiveData.setValue(null);

            }

        });
        return responseMutableLiveData;
    }


}
