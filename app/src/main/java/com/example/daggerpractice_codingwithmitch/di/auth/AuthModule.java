package com.example.daggerpractice_codingwithmitch.di.auth;

import com.example.daggerpractice_codingwithmitch.Network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    //AuthApi object will be destroy when it will come out of AuthModule that's the purpose of @AuthScope or @Singleton
    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
