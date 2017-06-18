package polokhachsergey.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

class AdapterHelper {

    private Context ctx;

    private final String ATTR_GROUP_SETTINGS = "groupSettings";
    private final String ATTR_ITEM_SETTINGS = "itemSettings";

    AdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter getAdapter() {

        // name groups
        String[] groups = new String[] {ctx.getString(R.string.groupBackground)};

        // name item
        String[] itemsBackground = new String[] { ctx.getString(R.string.itemGroupThemeApp)};

        ArrayList<Map<String, String>> groupData = new ArrayList<>();
        Map<String, String> m;
        for (String group : groups) {
            m = new HashMap<>();
            m.put(ATTR_GROUP_SETTINGS, group);
            groupData.add(m);
        }

        String groupFrom[] = new String[] {ATTR_GROUP_SETTINGS};

        int groupTo[] = new int[] {android.R.id.text1};

        ArrayList<ArrayList<Map<String, String>>> childData = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItem = new ArrayList<>();
        for (String background : itemsBackground) {
            m = new HashMap<>();
            m.put(ATTR_ITEM_SETTINGS, background);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = new String[] {ATTR_ITEM_SETTINGS};

        int childTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
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