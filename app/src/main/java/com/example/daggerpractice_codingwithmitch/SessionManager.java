package com.example.daggerpractice_codingwithmitch;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.ui.ApiCallResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    MediatorLiveData<ApiCallResource<User>> cachedUser = new MediatorLiveData<>();

    /**
     * we can either inject dependency by putting @Inject annotation in constructor of a specific class or
     * we can also make them dependent in any module using @Provide annotation
     **/

    @Inject
    public SessionManager() {
    }

    public void authenticationWithId(final LiveData<ApiCallResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(ApiCallResource.loading(null));

            cachedUser.addSource(source, new Observer<ApiCallResource<User>>() {
                @Override
                public void onChanged(ApiCallResource<User> userApiCallResource) {
                    cachedUser.setValue(userApiCallResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logout() {
        Log.d(TAG, "logout: Logging out...");
        cachedUser.setValue(ApiCallResource.logout());
    }

    public LiveData<ApiCallResource<User>> getAuthUser() {
        return cachedUser;
    }
}
