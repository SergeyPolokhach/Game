package com.example.polokhachsergey.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class SettingsActivity extends AppCompatActivity{

    // TAG for logcat
    private final String TAG_LIFECYCLE = "Lifecycle";
    
    private int backgroundFirstActivity;
    private int backgroundGuessNumberActivity;
//    private int textColorEvenButton;
//    private int textColorOddButton;
//    private int textColorStartButton;
//    private int textColorGiveUpButton;
    private int textColorTextView;

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onCreate()");

        // создаем адаптер
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        // нажатие на элемент
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,   int childPosition, long id) {

                onClick(groupPosition, childPosition);

                return false;
            }
        });

        loadSettings();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onResume()");
    }

    public void onClick(int groupPosition, int childPosition) {

        if (groupPosition == 0) {
            Intent intent;
            switch (childPosition) {
                case 0:
                    intent = new Intent(this, ListScreenImage.class);
                    startActivityForResult(intent, 1);
                    break;

                case 1:
                    intent = new Intent(this, ListScreenImage.class);
                    startActivityForResult(intent, 2);
                    break;
            }
        }
        if (groupPosition == 1) {
            Intent intent;
            switch (childPosition) {
                case 0:
                    intent = new Intent(this, ListColor.class);
                    startActivityForResult(intent, 3);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        if (requestCode == 1) {
            backgroundFirstActivity = data.getIntExtra("background", backgroundFirstActivity);

        }

        if (requestCode == 2) {
            backgroundGuessNumberActivity = data.getIntExtra("background", backgroundGuessNumberActivity);

        }

        if (requestCode == 3) {
            textColorTextView = data.getIntExtra("color", textColorTextView);

        }
    }


    private void saveSettings() {

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(getString(R.string.backgroundFirstActivity), backgroundFirstActivity);
        editor.putInt(getString(R.string.backgroundGuessNumberActivity), backgroundGuessNumberActivity);

        editor.putInt(getString(R.string.textColorTextView), textColorTextView);

        editor.commit();
    }

    private void loadSettings (){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);

        backgroundFirstActivity = sharedPreferences.getInt(getString(R.string.backgroundFirstActivity),  R.drawable.background_screen_2);
        backgroundGuessNumberActivity = sharedPreferences.getInt(getString(R.string.backgroundGuessNumberActivity),  R.drawable.background_screen_1);

        textColorTextView = sharedPreferences.getInt(getString(R.string.textColorTextView),  R.color.White);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onPause()");

        saveSettings();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onDestroy()");
    }
}