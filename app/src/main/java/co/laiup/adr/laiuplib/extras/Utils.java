package co.laiup.adr.laiuplib.extras;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Project LaiupLib
 * Created by Ha on 9/17/2015.
 */
public class Utils {

    // convert dp to px
    public static float convertDpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.densityDpi) / 160.0F;
    }

}
