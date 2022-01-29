package com.example.daggerpractice_codingwithmitch.di.main;

import android.app.Application;
import android.app.ProgressDialog;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.daggerpractice_codingwithmitch.Network.main.MainApi;
import com.example.daggerpractice_codingwithmitch.Util.VerticalSpacingItemDecoration;
import com.example.daggerpractice_codingwithmitch.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    private static final String TAG = "MainModule";

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter provideAdapter() {
        return new PostsRecyclerAdapter();
    }

    @MainScope
    @Provides
    static VerticalSpacingItemDecoration provideItemDecoration() {
        return new VerticalSpacingItemDecoration(15);
    }
}
