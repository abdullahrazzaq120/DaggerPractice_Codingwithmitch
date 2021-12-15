package com.example.daggerpractice_codingwithmitch.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Network.auth.AuthApi;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;

        if (authApi == null) {
            Log.d(TAG, "AuthViewModel: Auth Api is null");
        } else {
            Log.d(TAG, "AuthViewModel: Auth Api is not null");

//            authApi.getFakeRequest().enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    Log.d(TAG, "onResponse: Body: " + response.body());
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                }
//            });

        }

        Log.d(TAG, "AuthViewModel: ViewModel is working...");
    }
}
