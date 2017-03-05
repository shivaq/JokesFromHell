package com.yasuaki.gradle.jokesfromhell.ui;

import com.yasuaki.gradle.jokesfromhell.di.PerActivity;

/**
 * Created by Yasuaki on 2017/03/05.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> {

    void onAttachView(V MainMvpView);

    void onDetachView();
}
