package com.example.daggerpractice_codingwithmitch.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractice_codingwithmitch.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    /**
     * @Binds works same as @provides which returns a value. Lets just same it's a more efficient way of returning object
     */

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
