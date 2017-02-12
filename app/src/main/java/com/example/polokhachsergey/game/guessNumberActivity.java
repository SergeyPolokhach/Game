package com.example.polokhachsergey.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class guessNumberActivity extends AppCompatActivity {

    private TextView textView;

    private ImageView imageView;

    private Animation anim;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonStart;
    private Button button0;
    private Button buttonEnd;

    private int imageViewDrawableId;
    private int unknownNumber;
    private int counter;
    private int numberOfAttempts = 3;

    //
    private boolean showGuessNumberStartActivity = true;
    private boolean showMenu = false;

    // idContextMenu
    private final int ANSWER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number);

        textView = (TextView) findViewById(R.id.textView);

        imageView = (ImageView) findViewById(R.id.imageView);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        button0 = (Button) findViewById(R.id.button0);
        buttonEnd = (Button) findViewById(R.id.buttonEnd);

        showButtonAndMenu(showMenu);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button1:
                        act(1);
                        break;
                    case R.id.button2:
                        act(2);
                        break;
                    case R.id.button3:
                        act(3);
                        break;
                    case R.id.button4:
                        act(4);
                        break;
                    case R.id.button5:
                        act(5);
                        break;
                    case R.id.button6:
                        act(6);
                        break;
                    case R.id.button7:
                        act(7);
                        break;
                    case R.id.button8:
                        act(8);
                        break;
                    case R.id.button9:
                        act(9);
                        break;
                    case R.id.buttonStart:
                        initialization();
                        break;
                    case R.id.button0:
                        act(0);
                        break;
                    case R.id.buttonEnd:
                        giveUp();
                        break;
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        buttonStart.setOnClickListener(onClickListener);
        button0.setOnClickListener(onClickListener);
        buttonEnd.setOnClickListener(onClickListener);

        registerForContextMenu(buttonStart);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        textView.setText(savedInstanceState.getString("textView"));

        unknownNumber = savedInstanceState.getInt("unknownNumber");
        counter = savedInstanceState.getInt("counter");
        imageView.setImageResource(savedInstanceState.getInt("imageViewDrawableId"));

        showGuessNumberStartActivity = savedInstanceState.getBoolean("showGuessNumberStartActivity");
        showMenu = savedInstanceState.getBoolean("showMenu");

        showButtonAndMenu(showMenu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showGuessNumberStartActivity){
            showGuessNumberStartActivity = false;
            Intent intent = new Intent(this, guessNumberStartActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("textView", (String)textView.getText());
        outState.putInt("unknownNumber", unknownNumber);
        outState.putInt("counter", counter);
        outState.putInt("imageViewDrawableId", imageViewDrawableId);
        outState.putBoolean("showGuessNumberStartActivity", showGuessNumberStartActivity);
        outState.putBoolean("showMenu", showMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // hides or shows the menu group
        menu.setGroupVisible(R.id.group, showMenu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.helpNumber:
                if( unknownNumber % 2 == 0)
                    Toast.makeText(guessNumberActivity.this, R.string.menuHelp_h,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(guessNumberActivity.this, R.string.menuHelp_nh,Toast.LENGTH_SHORT).show();
                break;
            case R.id.helpText:
                Toast.makeText(guessNumberActivity.this, R.string.menuDescriptionAnswer,Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.buttonStart:
                menu.add(0, ANSWER, 0, R.string.contextMenuAnswer);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case ANSWER:
                Toast.makeText(guessNumberActivity.this, String.valueOf(unknownNumber),Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void initialization() {
        unknownNumber = new Random(System.currentTimeMillis()).nextInt(10);

        counter = 0;

        anim = AnimationUtils.loadAnimation(this,R.anim.mytrans);

        imageViewDrawableId = R.drawable.poz_3;
        imageView.setImageResource(imageViewDrawableId);
        imageView.startAnimation(anim);

        textView.setText(R.string.textStart);

        showButtonAndMenu(true);
    }

    public void showButtonAndMenu(boolean bool) {
        button1.setEnabled(bool);
        button2.setEnabled(bool);
        button3.setEnabled(bool);
        button4.setEnabled(bool);
        button5.setEnabled(bool);
        button6.setEnabled(bool);
        button7.setEnabled(bool);
        button8.setEnabled(bool);
        button9.setEnabled(bool);
        button0.setEnabled(bool);
        buttonEnd.setEnabled(bool);

        showMenu = bool;
    }

    public void giveUp() {
        String s = getString(R.string.giveUp) + String.valueOf(unknownNumber);
        textView.setText(s);

        anim = AnimationUtils.loadAnimation(this,R.anim.mytrans);

        imageViewDrawableId = R.drawable.poz_5;
        imageView.setImageResource(imageViewDrawableId);
        imageView.startAnimation(anim);

        showButtonAndMenu(false);
    }

    public void act(int n){
        if (unknownNumber == n) {
            textView.setText(R.string.textYes);

            anim = AnimationUtils.loadAnimation(this,R.anim.mycombo);

            imageViewDrawableId = R.drawable.poz_2;
            imageView.setImageResource(imageViewDrawableId);
            imageView.startAnimation(anim);

            showButtonAndMenu(false);
        } else {
            if ( ++counter != numberOfAttempts){
                if(numberOfAttempts - counter == 2) {
                    textView.setText(R.string.attempt2);

                    anim = AnimationUtils.loadAnimation(this,R.anim.myalpha);

                    imageViewDrawableId = R.drawable.neg_3;
                    imageView.setImageResource(imageViewDrawableId);
                    imageView.startAnimation(anim);
                } else {
                    textView.setText(R.string.attempt1);

                    anim = AnimationUtils.loadAnimation(this,R.anim.myscale);

                    imageViewDrawableId = R.drawable.neg_1;
                    imageView.setImageResource(imageViewDrawableId);
                    imageView.startAnimation(anim);
                }
            } else {
                String s = getString(R.string.attempt0) + String.valueOf(unknownNumber);
                textView.setText( s );

                anim = AnimationUtils.loadAnimation(this,R.anim.myrotate);

                imageViewDrawableId = R.drawable.neg_4;
                imageView.setImageResource(imageViewDrawableId);
                imageView.startAnimation(anim);

                showButtonAndMenu(false);
            }
        }
    }
}