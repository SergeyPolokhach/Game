package com.example.polokhachsergey.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListColor extends AppCompatActivity implements View.OnClickListener {

    private final String ATTRIBUTE_NAME_COLOR = "color";

    private final String[] color = {  "Aqua", "Black", "Red", "DarkRed", "Blue", "DarkBlue", "Green", "DarkGreen",
            "Brown", "Gold", "DarkGold", "Gray", "DarkGray", "Violet", "BlueViolet", "Chocolate"};
    private int textColor = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_color);

        Button buttonNext = (Button) findViewById(R.id.button);
        buttonNext.setOnClickListener(this);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(color.length);

        Map<String, Object> m;
        for (int i = 0; i < color.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_COLOR, color[i]);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_COLOR };

        int[] to = {R.id.ivText};

        MySimpleAdapter sAdapter = new MySimpleAdapter(this, data, R.layout.my_item_color, from, to);

        ListView lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);

        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        textColor =  R.color.Aqua;
                        break;
                    case 1:
                        textColor =  R.color.Black;
                        break;
                    case 2:
                        textColor =  R.color.Red;
                        break;
                    case 3:
                        textColor =  R.color.DarkRed;
                        break;
                    case 4:
                        textColor =  R.color.Blue;
                        break;
                    case 5:
                        textColor =  R.color.DarkBlue;
                        break;
                    case 6:
                        textColor =  R.color.Green;
                        break;
                    case 7:
                        textColor =  R.color.DarkGreen;
                        break;
                    case 8:
                        textColor =  R.color.Brown;
                        break;
                    case 9:
                        textColor =  R.color.Gold;
                        break;
                    case 10:
                        textColor =  R.color.DarkGoldenrod;
                        break;
                    case 11:
                        textColor =  R.color.Gray;
                        break;
                    case 12:
                        textColor =  R.color.DarkGray;
                        break;
                    case 13:
                        textColor =  R.color.Violet;
                        break;
                    case 14:
                        textColor =  R.color.BlueViolet;
                        break;
                    case 15:
                        textColor =  R.color.Chocolate;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (textColor == 0){
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("color", textColor);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    class MySimpleAdapter extends SimpleAdapter {
        MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);

            if (v.getId() == R.id.ivText) {
                switch (text) {
                    case "Aqua":
                        v.setTextColor( getResources().getColor(R.color.Aqua));
                        break;
                    case "Black":
                        v.setTextColor( getResources().getColor(R.color.Black));
                        break;
                    case "Red":
                        v.setTextColor( getResources().getColor(R.color.Red));
                        break;
                    case "DarkRed":
                        v.setTextColor( getResources().getColor(R.color.DarkRed));
                        break;
                    case "Blue":
                        v.setTextColor( getResources().getColor(R.color.Blue));
                        break;
                    case "DarkBlue":
                        v.setTextColor( getResources().getColor(R.color.DarkBlue));
                        break;
                    case "Green":
                        v.setTextColor( getResources().getColor(R.color.Green));
                        break;
                    case "DarkGreen":
                        v.setTextColor( getResources().getColor(R.color.DarkGreen));
                        break;
                    case "Brown":
                        v.setTextColor( getResources().getColor(R.color.Brown));
                        break;
                    case "Gold":
                        v.setTextColor( getResources().getColor(R.color.Gold));
                        break;
                    case "DarkGold":
                        v.setTextColor( getResources().getColor(R.color.DarkGoldenrod));
                        break;
                    case "Gray":
                        v.setTextColor( getResources().getColor(R.color.Gray));
                        break;
                    case "DarkGray":
                        v.setTextColor( getResources().getColor(R.color.DarkGray));
                        break;
                    case "Violet":
                        v.setTextColor( getResources().getColor(R.color.Violet));
                        break;
                    case "BlueViolet":
                        v.setTextColor( getResources().getColor(R.color.BlueViolet));
                        break;
                    case "Chocolate":
                        v.setTextColor( getResources().getColor(R.color.Chocolate));
                        break;
                }
            }
        }
    }
}