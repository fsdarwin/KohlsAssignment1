package com.example.kohlsassignment1.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerApplication;
import io.reactivex.annotations.Nullable;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }
}
