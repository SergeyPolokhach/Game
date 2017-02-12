package com.example.polokhachsergey.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class guessNumberStartActivity extends AppCompatActivity {

    TextView textStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        textStart = (TextView) findViewById(R.id.textStart);
    }
}
