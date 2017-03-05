package com.yasuaki.gradle.jokesfromhell.di.module;

import android.app.Application;

import dagger.Module;

/**
 * Created by Yasuaki on 2017/03/05.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

//    @Provides
//    @ApplicationContext
//    Context provideContext() {
//        return mApplication;
//    }
//
//    @Provides
//    Application provideApplication() {
//        return mApplication;
//    }
//
//    @Provides
//    @Singleton
//    DataManager provideDataManager(AppDataManager appDataManager) {
//        return appDataManager;
//    }

}
