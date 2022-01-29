package com.example.daggerpractice_codingwithmitch.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice_codingwithmitch.R;
import com.example.daggerpractice_codingwithmitch.ui.main.MainActivity;
import com.example.daggerpractice_codingwithmitch.viewmodels.ViewModelProviderFactory;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;
    TextInputEditText textInputEditText;
    ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    @Named("logo1")
    Drawable logo1;

    @Inject
    @Named("logo2")
    Drawable logo2;

    @Inject
    RequestManager requestManager;

    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);

        imageView1 = findViewById(R.id.login_logo1);
        imageView2 = findViewById(R.id.login_logo2);
        textInputEditText = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel.observeAuthState()
                .observe(AuthActivity.this, response -> {
                    if (response == null)
                        return;
                    switch (response.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Log.e(TAG, "onCreate: Error: User not exists. " + response.message);
                            break;
                        case AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onCreate: User: " + response.data.getEmail());
                            textInputEditText.setText(response.data.getEmail());
                            onLoginSuccess();
                            break;
                        case NOT_AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            break;
                    }
                });

        setLogo1();
        setLogo2();
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setLogo1() {

        requestManager
                .load(logo1)
                .into(imageView1);
    }

    private void setLogo2() {

        requestManager
                .load(logo2)
                .into(imageView2);
        Log.d(TAG, "setLogo2: " + logo2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
        }
    }

    private void login() {
        viewModel.authenticateWithId(Integer.parseInt(textInputEditText.getText().toString()));
    }
}