package polokhachsergey.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListScreenTheme extends AppCompatActivity implements View.OnClickListener {

    private final String ATTRIBUTE_NAME_IMAGE = "image";

    private int idTheme = -1;
    private final int[] idImage = {R.drawable.theme_scren_default, R.drawable.theme_scren_custom1, R.drawable.theme_scren_custom2,};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        Utils.onActivityCreateSetTheme(this, sharedPreferences.getInt(getString(R.string.idTheme), 0), false);
        setContentView(R.layout.activity_list_theme);

        Button buttonNext = (Button) findViewById(R.id.btnSetTheme);
        buttonNext.setOnClickListener(this);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>( idImage.length );

        Map<String, Object> m;
        for (int anImage : idImage) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_IMAGE, anImage);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_IMAGE };

        int[] to = { R.id.ivMyItemImage };

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.my_item_image, from, to);

        ListView lvSimple = (ListView) findViewById(R.id.lvImageTheme);
        lvSimple.setAdapter(sAdapter);

        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idTheme = position;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (idTheme == -1){
            finish();
        } else {

            Intent intent = new Intent();
            intent.putExtra(getString(R.string.idTheme), idTheme);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}