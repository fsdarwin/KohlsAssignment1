package com.example.kohlsassignment1.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.example.kohlsassignment1.data.model.Post;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    private final PostPostsitory PostPostsitory;
    private CompositeDisposable disposable;

    private final MutableLiveData<Post> selectedPost = new MutableLiveData<>();

    public LiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    @Inject
    public DetailsViewModel(PostRepository PostPostsitory) {
        this.PostPostsitory = PostPostsitory;
        disposable = new CompositeDisposable();
    }

    public void setSelectedPost(Post Post) {
        selectedPost.setValue(Post);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedPost.getValue() != null) {
            outState.putStringArray("Post_details", new String[] {
                    selectedPost.getValue().owner.login,
                    selectedPost.getValue().name
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedPost.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("Post_details")) {
                loadPost(savedInstanceState.getStringArray("Post_details"));
            }
        }
    }

    private void loadPost(String[] Post_details) {
        disposable.add(PostPostsitory.getPost(Post_details[0], Post_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Post>() {
                    @Override
                    public void onSuccess(Post value) {
                        selectedPost.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}