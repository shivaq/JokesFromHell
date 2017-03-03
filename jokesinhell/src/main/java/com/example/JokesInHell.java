package com.example;

import java.util.Random;

public class JokesInHell {

    public static String[] jokes = {
            "Yo momma is so fat, I took a picture of her last Christmas and it's still printing."
            , "Yo momma is so fat, when she sat on an iPod, she made the iPad!"
            , "Two hunters are out in the woods when one of them collapses. He doesn't seem to be breathing and his eyes are glazed. The other guy whips out his phone and calls the emergency services. He gasps, \"My friend is dead! What can I do?\" The operator says \"Calm down. I can help. First, let's make sure he's dead.\" There is a silence, then a shot is heard. Back on the phone, the guy says \"OK, now what?\""
            , "Anybody here named Jeff?\"\n" +
            "Jeff: \"Yes\"\n" +
            "Geoff: \"Yeos\""
    };


    public static String getJoke() {

        int rnd = new Random().nextInt(jokes.length);

        return jokes[rnd];
    }
}
