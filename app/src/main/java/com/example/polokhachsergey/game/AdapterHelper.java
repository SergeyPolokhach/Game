package com.example.polokhachsergey.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

class AdapterHelper {

    final String ATTR_GROUP_NAME= "groupName";
    final String ATTR_PHONE_NAME= "phoneName";

    // названия компаний (групп)
    String[] groups = new String[] {"Фон приложения", "Цвет текста"};

    // названия телефонов (элементов)
    String[] phon = new String[] { "Фон начального экрана", "Фон игры"};
    String[] text = new String[] {"Цвет текста в игре"};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;
    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список аттрибутов группы или элемента
    Map<String, String> m;

    Context ctx;

    AdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter adapter;


    SimpleExpandableListAdapter getAdapter() {

        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список аттрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group); // имя компании
            groupData.add(m);
        }

        // список аттрибутов групп для чтения
        String groupFrom[] = new String[] {ATTR_GROUP_NAME};
        // список ID view-элементов, в которые будет помещены аттрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};

        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список аттрибутов для каждого элемента
        for (String phone : phon) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone); // название телефона
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);
        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : text) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список аттрибутов элементов для чтения
        String childFrom[] = new String[] {ATTR_PHONE_NAME};
        // список ID view-элементов, в которые будет помещены аттрибуты элементов
        int childTo[] = new int[] {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        return adapter;
    }
}