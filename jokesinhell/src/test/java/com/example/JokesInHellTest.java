package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Yasuaki on 2017/02/24.
 */
@RunWith(JUnit4.class)
public class JokesInHellTest {

    @Test
    public void jokesHasNoNull() {
        String[] jokes = JokesInHell.jokes;
                for(String joke: jokes){
            assertThat(joke).isNotEmpty();
        }
    }
}