package com.example.polokhachsergey.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FirstActivity extends AppCompatActivity  implements OnClickListener{

    // TAG for logcat
    private final String TAG_LIFECYCLE = "Lifecycle";

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onCreate()");

        mRelativeLayout = (RelativeLayout)findViewById(R.id.activity_first);
        Button buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onResume()");
        loadSettings();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:
                Intent intent = new Intent(this, guessNumberActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void loadSettings (){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);

        mRelativeLayout.setBackgroundResource(sharedPreferences.getInt(getString(R.string.backgroundFirstActivity),
                                                                                 R.drawable.background_screen_2));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "ActivityFirst: onDestroy()");
    }
}