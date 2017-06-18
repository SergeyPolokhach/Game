package polokhachsergey.game;

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

    private int idTheme;

    ExpandableListView elvSettingsActivity;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        Utils.onActivityCreateSetTheme(this, sharedPreferences.getInt(getString(R.string.idTheme), 0), false);
        setContentView(R.layout.activity_settings);
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onCreate()");

        // создаем адаптер
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvSettingsActivity = (ExpandableListView) findViewById(R.id.elvSettingsActivity);
        elvSettingsActivity.setAdapter(adapter);

        // нажатие на элемент
        elvSettingsActivity.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
                    intent = new Intent(this, ListScreenTheme.class);
                    startActivityForResult(intent, 1);
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
            idTheme = data.getIntExtra(getString(R.string.idTheme), 0);
            saveSettings();
            Utils.changeToTheme(this);
        }
    }


    private void saveSettings() {

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(getString(R.string.idTheme), idTheme);

        editor.commit();
    }

    private void loadSettings (){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);

        idTheme = sharedPreferences.getInt(getString(R.string.idTheme),  0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "ActivitySetting: onPause()");
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