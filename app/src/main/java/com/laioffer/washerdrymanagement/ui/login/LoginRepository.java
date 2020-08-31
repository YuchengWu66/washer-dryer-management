package com.laioffer.washerdrymanagement.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.washerdrymanagement.base.BaseRepository;
import com.laioffer.washerdrymanagement.remote.response.RemoteResponse;
import com.laioffer.washerdrymanagement.remote.response.UserInfo;
//import com.laioffer.washerdrymanagement.remote.response.UserProfile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LoginRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<UserInfo>> login(LoginEvent loginEvent) {
        MutableLiveData<RemoteResponse<UserInfo>> responseMutableLiveData = new MutableLiveData<>();
        if (loginEvent == null) {
            return responseMutableLiveData;
        }
        Call<RemoteResponse<UserInfo>> call = apiService.login(loginEvent);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                responseMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RemoteResponse<UserInfo> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.setValue(errResponse);

            }

        });
        return responseMutableLiveData;
    }


}
