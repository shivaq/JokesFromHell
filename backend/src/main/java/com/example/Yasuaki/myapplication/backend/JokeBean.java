package com.example.yasuaki.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeBean {

    private String mJoke;

    public String getData() {
        return mJoke;
    }

    public void setData(String data) {
        mJoke = data;
    }
}