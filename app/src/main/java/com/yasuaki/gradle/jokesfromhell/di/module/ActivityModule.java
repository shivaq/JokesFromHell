package com.yasuaki.gradle.jokesfromhell.di.module;

import android.app.Activity;
import android.content.Context;

import com.yasuaki.gradle.jokesfromhell.di.ActivityContext;
import com.yasuaki.gradle.jokesfromhell.di.PerActivity;
import com.yasuaki.gradle.jokesfromhell.ui.MainMvpPresenter;
import com.yasuaki.gradle.jokesfromhell.ui.MainMvpView;
import com.yasuaki.gradle.jokesfromhell.ui.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yasuaki on 2017/03/05.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView>
                                                               presenter) {
        return presenter;
    }
}
