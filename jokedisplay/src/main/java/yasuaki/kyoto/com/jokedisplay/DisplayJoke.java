package yasuaki.kyoto.com.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.yasuaki.gradle.jokesfromhell.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        Intent intentFromMain = getIntent();
        String jokeFromHell = intentFromMain.getStringExtra(EXTRA_TEXT);
        TextView mJokeTv = (TextView) findViewById(R.id.text_joke);
        mJokeTv.setText(jokeFromHell);
    }
}
