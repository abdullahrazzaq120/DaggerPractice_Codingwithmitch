package com.example.daggerpractice_codingwithmitch.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.SessionManager;
import com.example.daggerpractice_codingwithmitch.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    //2nd way of injecting Session Manager is by @Inject object
    @Inject
    SessionManager sessionManager;

    @Inject
    public ProfileViewModel() {
        Log.d(TAG, "ProfileViewModel: ProfileViewModel is running...");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();
    }
}
