package com.example.daggerpractice_codingwithmitch.ui.main.posts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerpractice_codingwithmitch.Models.Post;
import com.example.daggerpractice_codingwithmitch.R;
import com.example.daggerpractice_codingwithmitch.Util.VerticalSpacingItemDecoration;
import com.example.daggerpractice_codingwithmitch.ui.ApiCallResource;
import com.example.daggerpractice_codingwithmitch.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";
    RecyclerView recyclerView;
    private PostsViewModel viewModel;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    VerticalSpacingItemDecoration itemDecoration;

    @Inject
    ViewModelProviderFactory providerFactory;

    protected ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribePosts();
    }

    private void subscribePosts() {
        viewModel.setPostCall();
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<ApiCallResource<List<Post>>>() {
            @Override
            public void onChanged(ApiCallResource<List<Post>> response) {
                if (response == null)
                    return;
                switch (response.status) {
                    case ERROR:
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), response.message, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onChanged: Error: " + response.message);
                        break;
                    case SUCCESS:
                        progressDialog.dismiss();
                        adapter.setPosts(response.data);
                        break;
                    case LOADING:
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);
    }
}
