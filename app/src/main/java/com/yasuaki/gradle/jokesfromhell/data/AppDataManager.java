package com.yasuaki.gradle.jokesfromhell.data;

import android.content.Context;

import com.yasuaki.gradle.jokesfromhell.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Yasuaki on 2017/03/05.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;

    @Inject
    public AppDataManager(@ApplicationContext Context context) {
        mContext = context;
    }
}
