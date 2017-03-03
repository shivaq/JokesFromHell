package com.yasuaki.gradle.jokesfromhell;

import com.example.JokesInHell;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by Yasuaki on 2017/03/02.
 */

public class MainFragmentTest {

    @Test
    public void isJokeReturned(){
        assertThat(JokesInHell.getJoke()).isInstanceOf(String.class);
    }

}
