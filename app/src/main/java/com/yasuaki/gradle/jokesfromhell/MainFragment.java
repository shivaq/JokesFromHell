package com.yasuaki.gradle.jokesfromhell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.JokesInHell;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yasuaki.kyoto.com.jokedisplay.DisplayJoke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public static final String EXTRA_TEXT = "com.yasuaki.gradle.jokesfromhell.EXTRA_TEXT";

    @BindView(R.id.btn_launch_joke_activity)
    Button mLaunchJokeBtn;
    @BindView(R.id.adView)
    AdView mAdView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Manfred"));
        return root;
    }
    @OnClick(R.id.btn_launch_joke_activity)
    void onItemClicked() {
        String joke = JokesInHell.getJoke();
        Intent startIntent = new Intent(getActivity(), DisplayJoke.class);
        startIntent.putExtra(EXTRA_TEXT, joke);
        startActivity(startIntent);
    }
}
