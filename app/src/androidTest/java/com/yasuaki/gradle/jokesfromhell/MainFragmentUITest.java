package com.yasuaki.gradle.jokesfromhell;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import yasuaki.kyoto.com.jokedisplay.DisplayJoke;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Yasuaki on 2017/02/28.
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentUITest {

    @Rule
    public IntentsTestRule<DisplayJoke> mDisplayActivityIntentsTestRule =
            new IntentsTestRule<>(DisplayJoke.class);

    @Rule
    public ActivityTestRule<MainActivity> mMainFragmentTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, false);

    private void startMainFragmentWithExtra(){
        Intent startIntent = new Intent();
        startIntent.putExtra(MainFragment.EXTRA_TEXT, "joke");
        mMainFragmentTestRule.launchActivity(startIntent);
    }

    @Test
    public void isDisplayActivityDisplayedWithExtra(){
        //Given
        startMainFragmentWithExtra();
        //When
        onView(withId(R.id.btn_launch_joke_activity)).perform(click());
        //Then
        intended(hasComponent("yasuaki.kyoto.com.jokedisplay.DisplayJoke"));
        intended(hasExtra(MainFragment.EXTRA_TEXT, "joke"));
        onView(withId(R.id.text_joke)).check(matches(isDisplayed()));
    }
}