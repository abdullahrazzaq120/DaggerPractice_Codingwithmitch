package com.example.daggerpractice_codingwithmitch.di;

import com.example.daggerpractice_codingwithmitch.di.auth.AuthModule;
import com.example.daggerpractice_codingwithmitch.di.auth.AuthScope;
import com.example.daggerpractice_codingwithmitch.di.auth.AuthViewModelsModule;
import com.example.daggerpractice_codingwithmitch.di.main.MainFragmentBuilderModule;
import com.example.daggerpractice_codingwithmitch.di.main.MainModule;
import com.example.daggerpractice_codingwithmitch.di.main.MainScope;
import com.example.daggerpractice_codingwithmitch.di.main.MainViewModelModules;
import com.example.daggerpractice_codingwithmitch.ui.main.MainActivity;
import com.example.daggerpractice_codingwithmitch.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuilderModule.class, MainViewModelModules.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
