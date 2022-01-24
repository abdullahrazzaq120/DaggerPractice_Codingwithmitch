package com.example.daggerpractice_codingwithmitch.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.di.ViewModelKey;
import com.example.daggerpractice_codingwithmitch.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);
}
