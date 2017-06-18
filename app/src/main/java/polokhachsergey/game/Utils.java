package polokhachsergey.game;

import android.app.Activity;
import android.content.Intent;

class Utils {
    static int sTheme;
    private final static int THEME_DEFAULT = 0;
    private final static int THEME_CUSTOM_1 = 1;
    private final static int THEME_NO_ACTION_BAR = 2;
    /** * Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
    static void changeToTheme(Activity activity) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    static void onActivityCreateSetTheme(Activity activity, int theme, boolean flag) {
        if (flag) {
            sTheme = theme;
        }
        switch (theme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_CUSTOM_1:
                activity.setTheme(R.style.AppTheme_Custom1);
                break;
            case THEME_NO_ACTION_BAR:
                activity.setTheme(R.style.AppTheme_NoActionBar);
                break;
        }
    }
}
