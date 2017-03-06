package com.yasuaki.gradle.jokesfromhell.di.module;

import android.app.Application;
import android.content.Context;

import com.yasuaki.gradle.jokesfromhell.data.AppDataManager;
import com.yasuaki.gradle.jokesfromhell.data.DataManager;
import com.yasuaki.gradle.jokesfromhell.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yasuaki on 2017/03/05.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
