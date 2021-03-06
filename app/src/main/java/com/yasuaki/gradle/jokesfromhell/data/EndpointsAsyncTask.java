package com.yasuaki.gradle.jokesfromhell.data;


import android.os.AsyncTask;

import com.example.yasuaki.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Yasuaki on 2017/03/03.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String>{

    private static JokeApi jokeApiService = null;

    private fetchDataListener mFetchDataListener = null;
    private Exception mFetchDataError = null;

    @Inject
    public EndpointsAsyncTask(){
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApiService = builder.build();
        }
        try {
            return jokeApiService.pullJokeFromHell().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.mFetchDataListener != null) {
            this.mFetchDataListener.onComplete(result, mFetchDataError);
            Timber.d("EndpointsAsyncTask:onPostExecute: joke is %s", result);
        }
    }

    @Override
    protected void onCancelled() {
        if(this.mFetchDataListener != null){
            this.mFetchDataListener.onComplete(null, mFetchDataError);
        }
        super.onCancelled();
    }

    public interface fetchDataListener {
        void onComplete(String result, Exception e);
    }

    public EndpointsAsyncTask setListener(fetchDataListener listener) {
        this.mFetchDataListener = listener;
        return this;
    }
}