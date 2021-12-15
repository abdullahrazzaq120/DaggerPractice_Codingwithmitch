package com.example.daggerpractice_codingwithmitch.di;

import com.example.daggerpractice_codingwithmitch.di.auth.AuthModule;
import com.example.daggerpractice_codingwithmitch.di.auth.AuthViewModelsModule;
import com.example.daggerpractice_codingwithmitch.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();
}
