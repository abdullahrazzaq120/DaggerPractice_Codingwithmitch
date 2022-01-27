package com.example.daggerpractice_codingwithmitch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggerpractice_codingwithmitch.Models.User;
import com.example.daggerpractice_codingwithmitch.ui.auth.AuthActivity;
import com.example.daggerpractice_codingwithmitch.ui.ApiCallResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    protected SessionManager sessionManager;
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager.getAuthUser().observe(this, new Observer<ApiCallResource<User>>() {
            @Override
            public void onChanged(ApiCallResource<User> userApiCallResource) {
                if (userApiCallResource == null)
                    return;
                switch (userApiCallResource.status) {
                    case LOADING:
                        break;
                    case ERROR:
                        Log.e(TAG, "onCreate: Error: User not exists.");
                        break;
                    case AUTHENTICATED:
                        Log.d(TAG, "onCreate: Login Success");
                        break;
                    case NOT_AUTHENTICATED:
                        navLoginScreen();
                        break;
                }
            }
        });
    }

    private void navLoginScreen() {
        Intent intent = new Intent(BaseActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
