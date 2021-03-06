package com.yasuaki.gradle.jokesfromhell.di.component;

import android.app.Application;
import android.content.Context;

import com.yasuaki.gradle.jokesfromhell.MvpApplication;
import com.yasuaki.gradle.jokesfromhell.data.DataManager;
import com.yasuaki.gradle.jokesfromhell.di.ApplicationContext;
import com.yasuaki.gradle.jokesfromhell.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yasuaki on 2017/03/05.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApplication mvpApplication);

    @ApplicationContext
    Context context();

    Application application();
    DataManager dataManager();

}
