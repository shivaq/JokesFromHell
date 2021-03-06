package com.yasuaki.gradle.jokesfromhell;

import android.app.Application;

import com.yasuaki.gradle.jokesfromhell.data.DataManager;
import com.yasuaki.gradle.jokesfromhell.di.component.ApplicationComponent;
import com.yasuaki.gradle.jokesfromhell.di.component.DaggerApplicationComponent;
import com.yasuaki.gradle.jokesfromhell.di.module.ApplicationModule;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Yasuaki on 2017/03/05.
 */

public class MvpApplication extends Application{

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ApplicationComponent getComponent(){
        return mApplicationComponent;
    }
}
