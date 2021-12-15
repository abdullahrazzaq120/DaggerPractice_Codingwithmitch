package com.example.daggerpractice_codingwithmitch.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice_codingwithmitch.R;
import com.example.daggerpractice_codingwithmitch.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);

        imageView = findViewById(R.id.login_logo);
        setLogo();
    }

    private void setLogo() {

        requestManager
                .load(logo)
                .into(imageView);
    }
}