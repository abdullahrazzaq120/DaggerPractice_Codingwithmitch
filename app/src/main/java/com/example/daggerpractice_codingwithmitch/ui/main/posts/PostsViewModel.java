package com.example.daggerpractice_codingwithmitch.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice_codingwithmitch.Models.Post;
import com.example.daggerpractice_codingwithmitch.Network.main.MainApi;
import com.example.daggerpractice_codingwithmitch.SessionManager;
import com.example.daggerpractice_codingwithmitch.ui.ApiCallResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    private MediatorLiveData<ApiCallResource<List<Post>>> postsLiveData;

    //inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        postsLiveData = new MediatorLiveData<>();
        Log.d(TAG, "PostsViewModel: ViewModel is working...");
    }

    public void setPostCall() {
        postsLiveData.setValue(ApiCallResource.loading(null));
        Call<List<Post>> postsCall = mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId());
        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        postsLiveData.setValue(ApiCallResource.success(response.body()));
                    } else {
                        postsLiveData.setValue(ApiCallResource.error(response.message(), null));
                    }
                } else {
                    postsLiveData.setValue(ApiCallResource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postsLiveData.setValue(ApiCallResource.error(t.getMessage(), null));
            }
        });
    }

    public LiveData<ApiCallResource<List<Post>>> observePosts() {
        return postsLiveData;
    }
}
