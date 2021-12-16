package com.example.daggerpractice_codingwithmitch.ui.auth;

import android.arch.lifecycle.LiveDataReactiveStreams;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.Network.auth.AuthApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;

        Log.d(TAG, "AuthViewModel: ViewModel is working...");

        if (authApi == null) {
            Log.d(TAG, "AuthViewModel: Auth Api is null");
        } else {
            Log.d(TAG, "AuthViewModel: Auth Api is not null");
        }
    }

    public void authenticateWithId(int id) {

        Call<User> userCall = authApi.getUser(id);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Email: " + response.body().getEmail());

                        User user = new User();
                        user.setEmail(response.body().getEmail());
                        user.setUsername(response.body().getUsername());
                        user.setId(response.body().getId());
                        user.setWebsite(response.body().getWebsite());

                        authUser.setValue(user);
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessful: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<User> observeUser() {
        return authUser;
    }
}
