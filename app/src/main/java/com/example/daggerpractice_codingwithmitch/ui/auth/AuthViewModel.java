package com.example.daggerpractice_codingwithmitch.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.Network.auth.AuthApi;
import com.example.daggerpractice_codingwithmitch.SessionManager;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    SessionManager sessionManager;
    MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        Log.d(TAG, "AuthViewModel: AuthViewModel is running...");
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int id) {
        Log.d(TAG, "authenticateWithId: Logging in...");
        sessionManager.authenticationWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int id) {

        Call<User> userCall = authApi.getUser(id);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        User user = new User();
                        user.setEmail(response.body().getEmail());
                        user.setUsername(response.body().getUsername());
                        user.setId(response.body().getId());
                        user.setWebsite(response.body().getWebsite());

                        authUser.setValue(AuthResource.authenticated(user));
                    } else {
                        authUser.setValue(AuthResource.error(response.message(), null));
                    }
                } else {
                    authUser.setValue(AuthResource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                authUser.setValue(AuthResource.error(t.getMessage(), null));
            }
        });

        return authUser;
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
