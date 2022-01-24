package com.example.daggerpractice_codingwithmitch.di.main;

import com.example.daggerpractice_codingwithmitch.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}
