package polokhachsergey.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessNumberActivity extends AppCompatActivity {

    private TextView tvGuessNumberActivity;
    private ImageView ivGuessNumberActivity;
    private Animation anim;
    private SharedPreferences sharedPreferences;
    private RelativeLayout mRelativeLayout;

    private DialogFragment descriptionDialog;

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_Start;
    private Button btn_0;
    private Button btn_GiveUp;

    private int imageViewDrawableId;
    private int unknownNumber;
    private int counter;
    // Statistical variables
    private int currentStatisticPoz = 0;
    private int currentStatisticNeg = 0;
    private int allStatisticPoz;
    private int allStatisticNeg;
    // idContextMenu
    private final int ANSWER = 1;
    private final int STATISTIC = 2;

    // Boolean variables display a description of the game and show the menu groups
    private boolean showDescriptionDialog = true;
    private boolean showGroupMenu = false;

    // TAG for logcat
    private final String TAG_LIFECYCLE = "Lifecycle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        Utils.onActivityCreateSetTheme(this, sharedPreferences.getInt(getString(R.string.idTheme), 0), true);
        setContentView(R.layout.activity_guess_number);

        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onCreate()");

        descriptionDialog = new DescriptionDialog();

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_guess_number);

        tvGuessNumberActivity = (TextView) findViewById(R.id.tvGuessNumberActivity);

        ivGuessNumberActivity = (ImageView) findViewById(R.id.ivGuessNumberActivity);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_Start = (Button) findViewById(R.id.btn_Start);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_GiveUp = (Button) findViewById(R.id.btn_GiveUp);

        showButtonAndMenu(showGroupMenu);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_1:
                        btn_1.setVisibility(View.INVISIBLE);
                        act(1);
                        break;
                    case R.id.btn_2:
                        btn_2.setVisibility(View.INVISIBLE);
                        act(2);
                        break;
                    case R.id.btn_3:
                        btn_3.setVisibility(View.INVISIBLE);
                        act(3);
                        break;
                    case R.id.btn_4:
                        btn_4.setVisibility(View.INVISIBLE);
                        act(4);
                        break;
                    case R.id.btn_5:
                        btn_5.setVisibility(View.INVISIBLE);
                        act(5);
                        break;
                    case R.id.btn_6:
                        btn_6.setVisibility(View.INVISIBLE);
                        act(6);
                        break;
                    case R.id.btn_7:
                        btn_7.setVisibility(View.INVISIBLE);
                        act(7);
                        break;
                    case R.id.btn_8:
                        btn_8.setVisibility(View.INVISIBLE);
                        act(8);
                        break;
                    case R.id.btn_9:
                        btn_9.setVisibility(View.INVISIBLE);
                        act(9);
                        break;
                    case R.id.btn_Start:
                        initialization();
                        break;
                    case R.id.btn_0:
                        btn_0.setVisibility(View.INVISIBLE);
                        act(0);
                        break;
                    case R.id.btn_GiveUp:
                        giveUp();
                        break;
                }
            }
        };

        btn_1.setOnClickListener(onClickListener);
        btn_2.setOnClickListener(onClickListener);
        btn_3.setOnClickListener(onClickListener);
        btn_4.setOnClickListener(onClickListener);
        btn_5.setOnClickListener(onClickListener);
        btn_6.setOnClickListener(onClickListener);
        btn_7.setOnClickListener(onClickListener);
        btn_8.setOnClickListener(onClickListener);
        btn_9.setOnClickListener(onClickListener);
        btn_Start.setOnClickListener(onClickListener);
        btn_0.setOnClickListener(onClickListener);
        btn_GiveUp.setOnClickListener(onClickListener);

        registerForContextMenu(btn_Start);

        loadStatistic();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onRestart()");

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        if (sharedPreferences.getInt(getString(R.string.idTheme), 0) != Utils.sTheme) {
            Utils.changeToTheme(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onResume()");

        if (showDescriptionDialog) {
            descriptionDialog.show(getFragmentManager(), "dialogFragment");
            showDescriptionDialog = false;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvGuessNumberActivity.setText(savedInstanceState.getString("textView"));

        unknownNumber = savedInstanceState.getInt("unknownNumber");
        counter = savedInstanceState.getInt("counter");
        currentStatisticPoz = savedInstanceState.getInt("statisticPoz");
        currentStatisticNeg = savedInstanceState.getInt("statisticNeg");
        imageViewDrawableId = savedInstanceState.getInt("imageViewDrawableId");
        ivGuessNumberActivity.setImageResource(imageViewDrawableId);

        showDescriptionDialog = savedInstanceState.getBoolean("showDescriptionDialog");
        showGroupMenu = savedInstanceState.getBoolean("showGroupMenu");

        showButtonAndMenu(showGroupMenu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("textView", (String) tvGuessNumberActivity.getText());

        outState.putInt("unknownNumber", unknownNumber);
        outState.putInt("counter", counter);
        outState.putInt("imageViewDrawableId", imageViewDrawableId);
        outState.putInt("statisticPoz", currentStatisticPoz);
        outState.putInt("statisticNeg", currentStatisticNeg);

        outState.putBoolean("showDescriptionDialog", showDescriptionDialog);
        outState.putBoolean("showGroupMenu", showGroupMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Hides or shows the menu group
        menu.setGroupVisible(R.id.group, showGroupMenu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast;
        String str;

        switch (id) {
            case R.id.helpNumber:
                if (unknownNumber % 2 == 0) {
                    toast = Toast.makeText(GuessNumberActivity.this, R.string.menuHelp_h, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    toast = Toast.makeText(GuessNumberActivity.this, R.string.menuHelp_nh, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.helpStatisticAll:
                str = getString(R.string.allStatisticAll) + String.valueOf(allStatisticPoz + allStatisticNeg) + "\n" +
                        getString(R.string.allStatisticPoz) + String.valueOf(allStatisticPoz) + "\n" +
                        getString(R.string.allStatisticNeg) + String.valueOf(allStatisticNeg);
                toast = Toast.makeText(GuessNumberActivity.this, str, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.helpStatistic:
                str = getString(R.string.currentStatisticAll) + String.valueOf(currentStatisticPoz + currentStatisticNeg) + "\n" +
                        getString(R.string.currentStatisticPoz) + String.valueOf(currentStatisticPoz) + "\n" +
                        getString(R.string.currentStatisticNeg) + String.valueOf(currentStatisticNeg);
                toast = Toast.makeText(GuessNumberActivity.this, str, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.helpText:
                toast = Toast.makeText(GuessNumberActivity.this, R.string.menuDescriptionAnswer, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.btn_Start:
                menu.add(0, ANSWER, 0, R.string.contextMenuAnswer);
                menu.add(0, STATISTIC, 0, R.string.contextMenuStatistic);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast toast;
        switch (item.getItemId()) {
            case ANSWER:
                toast = Toast.makeText(GuessNumberActivity.this, getString(R.string.contextMenuAnswerToast) + " " + String.valueOf(unknownNumber), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case STATISTIC:
                saveStatistic(false);
                toast = Toast.makeText(GuessNumberActivity.this, R.string.contextMenuStatisticToast, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initialization() {
        unknownNumber = new Random(System.currentTimeMillis()).nextInt(10);

        counter = 0;

        anim = AnimationUtils.loadAnimation(this, R.anim.mycombo_alpha_trans);

        imageViewDrawableId = R.drawable.poz_3;
        ivGuessNumberActivity.setImageResource(imageViewDrawableId);
        ivGuessNumberActivity.startAnimation(anim);

        tvGuessNumberActivity.setText(R.string.textStart);

        showButtonAndMenu(true);
    }

    private void showButtonAndMenu(boolean bool) {
        int id;
        if (bool) {
            id = View.VISIBLE;
        } else {
            id = View.INVISIBLE;
        }

        btn_1.setVisibility(id);
        btn_2.setVisibility(id);
        btn_3.setVisibility(id);
        btn_4.setVisibility(id);
        btn_5.setVisibility(id);
        btn_6.setVisibility(id);
        btn_7.setVisibility(id);
        btn_8.setVisibility(id);
        btn_9.setVisibility(id);
        btn_0.setVisibility(id);
        btn_GiveUp.setVisibility(id);

        showGroupMenu = bool;
    }

    private void giveUp() {
        String s = getString(R.string.giveUp) + " " + String.valueOf(unknownNumber);
        tvGuessNumberActivity.setText(s);

        anim = AnimationUtils.loadAnimation(this, R.anim.mycombo_trans_scale);

        imageViewDrawableId = R.drawable.poz_5;
        ivGuessNumberActivity.setImageResource(imageViewDrawableId);
        ivGuessNumberActivity.startAnimation(anim);

        currentStatisticNeg++;
        allStatisticNeg++;

        showButtonAndMenu(false);
    }

    private void act(int n) {
        if (unknownNumber == n) {
            tvGuessNumberActivity.setText(R.string.textYes);

            anim = AnimationUtils.loadAnimation(this, R.anim.mycombo_rotate_scale);

            imageViewDrawableId = R.drawable.poz_2;
            ivGuessNumberActivity.setImageResource(imageViewDrawableId);
            ivGuessNumberActivity.startAnimation(anim);

            currentStatisticPoz++;
            allStatisticPoz++;

            showButtonAndMenu(false);
        } else {
            if (++counter != 3) {
                if (counter == 1) {
                    tvGuessNumberActivity.setText(R.string.attempt2);

                    anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);

                    imageViewDrawableId = R.drawable.neg_3;
                    ivGuessNumberActivity.setImageResource(imageViewDrawableId);
                    ivGuessNumberActivity.startAnimation(anim);
                } else {
                    tvGuessNumberActivity.setText(R.string.attempt1);

                    anim = AnimationUtils.loadAnimation(this, R.anim.myscale);

                    imageViewDrawableId = R.drawable.neg_1;
                    ivGuessNumberActivity.setImageResource(imageViewDrawableId);
                    ivGuessNumberActivity.startAnimation(anim);
                }
            } else {
                String s = getString(R.string.attempt0) + " " + String.valueOf(unknownNumber);
                tvGuessNumberActivity.setText(s);

                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);

                imageViewDrawableId = R.drawable.neg_4;
                ivGuessNumberActivity.setImageResource(imageViewDrawableId);
                ivGuessNumberActivity.startAnimation(anim);

                currentStatisticNeg++;
                allStatisticNeg++;

                showButtonAndMenu(false);
            }
        }
    }

    private void saveStatistic(boolean flag) {
        if (flag) {
            sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("allStatisticPoz", allStatisticPoz);
            editor.putInt("allStatisticNeg", allStatisticNeg);

            editor.commit();
        } else {
            allStatisticPoz = 0;
            allStatisticNeg = 0;

            currentStatisticPoz = 0;
            currentStatisticNeg = 0;
        }
    }

    private void loadStatistic() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        allStatisticPoz = sharedPreferences.getInt("allStatisticPoz", 0);
        allStatisticNeg = sharedPreferences.getInt("allStatisticNeg", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "GuessNumberActivity: onDestroy()");

        saveStatistic(true);
    }
}