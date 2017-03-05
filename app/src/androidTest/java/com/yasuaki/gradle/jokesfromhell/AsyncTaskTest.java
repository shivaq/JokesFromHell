package com.yasuaki.gradle.jokesfromhell;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.yasuaki.gradle.jokesfromhell.data.EndpointsAsyncTask;
import com.yasuaki.gradle.jokesfromhell.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    String mJoke = null;
    Exception mError = null;
    CountDownLatch mLatch = null;

    Context mContext = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, true);

    @Before
    public void setUp(){
        mLatch = new CountDownLatch(1);
    }

    @After
    public void tearDown(){
        mLatch.countDown();
    }

    @Test
    public void isJokeFetchedFromGCE(){
        // Given
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();

        //When
        jokeTask.setListener(new EndpointsAsyncTask.fetchDataListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mJoke = result;
                mError = e;
                mLatch.countDown();
            }
        }).execute();

        // Then
        assertThat(mError).isNull();
        assertThat(mJoke).isNotEmpty();
        assertThat(mJoke).isInstanceOf(String.class);
    }
}