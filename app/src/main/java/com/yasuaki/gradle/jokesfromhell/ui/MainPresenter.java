package com.yasuaki.gradle.jokesfromhell.ui;

import com.yasuaki.gradle.jokesfromhell.data.EndpointsAsyncTask;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView>implements MainMvpPresenter<V>{

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

    @Override
    public void fetchJoke() {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute();

        endpointsAsyncTask.setListener(new EndpointsAsyncTask.fetchDataListener() {
            @Override
            public void onComplete(String result, Exception e) {
                getMvpView().intentJokeDisplay(result);
            }
        });
    }

    private V getMvpView() {
        return mMainMvpView;
    }
}
