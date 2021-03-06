package com.laioffer.washerdrymanagement.ui.register;



import androidx.lifecycle.MutableLiveData;

import com.laioffer.washerdrymanagement.base.BaseRepository;
import com.laioffer.washerdrymanagement.remote.response.RemoteResponse;
import com.laioffer.washerdrymanagement.remote.response.UserInfo;
import com.laioffer.washerdrymanagement.ui.login.LoginEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RegisterRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<UserInfo>> register(RegisterEvent registerEvent) {
        MutableLiveData<RemoteResponse<UserInfo>> responseMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<UserInfo>> call =
                apiService.register(registerEvent);
        call.enqueue(new Callback<RemoteResponse<UserInfo>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<UserInfo>> call,
                                   Response<RemoteResponse<UserInfo>> response) {
                responseMutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<UserInfo>> call, Throwable t) {
                RemoteResponse<UserInfo> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.setValue(errResponse);
            }
        });
        return responseMutableLiveData;
    }

}
