package com.example.kohlsassignment1.di.component;

import android.app.Application;

import com.example.kohlsassignment1.base.BaseApplication;
import com.example.kohlsassignment1.di.module.ActivityBindingModule;
import com.example.kohlsassignment1.di.module.ApplicationModule;
import com.example.kohlsassignment1.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
