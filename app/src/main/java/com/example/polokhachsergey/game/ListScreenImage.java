package com.example.polokhachsergey.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListScreenImage extends AppCompatActivity implements View.OnClickListener {

    private final String ATTRIBUTE_NAME_IMAGE = "image";

    private int background = 0;
    private final int[] image = {R.drawable.background_screen_1, R.drawable.background_screen_2, R.drawable.background_screen_3,
            R.drawable.background_screen_4, R.drawable.background_screen_5};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen_image);

        Button buttonNext = (Button) findViewById(R.id.buttonSetImage);
        buttonNext.setOnClickListener(this);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>( image.length );

        Map<String, Object> m;
        for (int i = 0; i < image.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_IMAGE, image[i]);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_IMAGE };

        int[] to = { R.id.myItemImageView };

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.my_item_image, from, to);

        ListView lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);

        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                background = image[position];
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (background == 0){
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("background", background);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}