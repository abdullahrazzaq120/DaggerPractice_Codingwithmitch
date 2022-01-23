package com.example.daggerpractice_codingwithmitch.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.daggerpractice_codingwithmitch.BaseActivity;
import com.example.daggerpractice_codingwithmitch.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
