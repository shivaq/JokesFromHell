package com.yasuaki.gradle.jokesfromhell.ui;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView>implements MainMvpPresenter<V>{

//    @Inject
//    public MainPresenter(DataManager dataManager) {
//        super(dataManager);
//    }

    private V mMainMvpView;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void onAttachView(V mvpView) {
        mMainMvpView = mvpView;
    }

    @Override
    public void onDetachView() {
        mMainMvpView = null;
    }

    public boolean isViewAttached() {
        return mMainMvpView != null;
    }

    public V getMvpView() {
        return mMainMvpView;
    }
    //todo:EndPointsAsyncTask を インジェクトさせる
}
