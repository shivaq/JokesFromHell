package com.yasuaki.gradle.jokesfromhell.di.component;

import com.yasuaki.gradle.jokesfromhell.di.PerActivity;
import com.yasuaki.gradle.jokesfromhell.di.module.ActivityModule;
import com.yasuaki.gradle.jokesfromhell.ui.MainActivity;

import dagger.Component;

/**
 * Created by Yasuaki on 2017/03/05.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
