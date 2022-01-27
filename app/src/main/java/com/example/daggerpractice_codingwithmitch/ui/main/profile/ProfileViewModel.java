package com.example.daggerpractice_codingwithmitch.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.SessionManager;
import com.example.daggerpractice_codingwithmitch.ui.ApiCallResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    //2nd way of injecting Session Manager is by @Inject constructor
    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

        Log.d(TAG, "ProfileViewModel: ViewModel is working...");
    }

    public LiveData<ApiCallResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();
    }
}
