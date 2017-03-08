package com.yasuaki.gradle.jokesfromhell.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yasuaki.gradle.jokesfromhell.MvpApplication;
import com.yasuaki.gradle.jokesfromhell.R;
import com.yasuaki.gradle.jokesfromhell.di.component.ActivityComponent;
import com.yasuaki.gradle.jokesfromhell.di.component.DaggerActivityComponent;
import com.yasuaki.gradle.jokesfromhell.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yasuaki.kyoto.com.jokedisplay.DisplayJoke;


public class MainActivity extends AppCompatActivity
        implements MainMvpView {

    public static final String EXTRA_TEXT = "com.yasuaki.gradle.jokesfromhell.EXTRA_TEXT";

    private ActivityComponent mActivityComponent;
    private String mJoke;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.btn_launch_joke_activity)
    Button mLaunchJokeBtn;
    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.loading_spinner)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((MvpApplication) getApplication()).getComponent())
                    .build();
        }

        mActivityComponent.inject(this);
        mPresenter.onAttachView(this);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_launch_joke_activity)
    public void onItemClicked() {
        showProgressBar();
        mPresenter.fetchJoke();
    }

    @Override
    public void storeJokeAndGoIntent(String joke) {
        mJoke = joke;
            intentToDisplayActivity();
    }

    public void intentToDisplayActivity(){
        hideProgressBar();
        Intent startIntent = new Intent(this, DisplayJoke.class);
        startIntent.putExtra(EXTRA_TEXT, mJoke);
        startActivity(startIntent);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}
