package com.example.kohlsassignment1.di.module;

import com.example.kohlsassignment1.MainActivity;
import com.example.kohlsassignment1.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}