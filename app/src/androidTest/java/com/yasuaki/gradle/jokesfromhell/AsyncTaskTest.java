package com.yasuaki.gradle.jokesfromhell;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Yasuaki on 2017/03/03.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    String mJoke = null;
    Exception mError = null;
    CountDownLatch mLatch = null;

    Context mContext = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<MainActivity> mMainFragmentTestRule =
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
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        jokeTask.setListener(new EndpointsAsyncTask.fetchDataListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mJoke = result;
                mError = e;
                mLatch.countDown();
            }
        }).execute(new Pair<Context, String>(mContext, "Manfred"));

        onView(withText("Hi, Manfred")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//        onView(withText("Manfred")).inRoot(withDecorView‌​(not(getActivity().g‌​etWindow().getDecorV‌​iew()))) .check(matches(isDisplayed()));

        // When
        // GCE にアクセスした時


        // Then
        // String を取得できる
    }
}
