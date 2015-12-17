package co.laiup.adr.laiuplib.Log;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Project android-common-controls
 * Created by Ha on 9/17/2015.
 */
public class L {
    public static final String TAG = "Laiup";
    public static void m(String message) {
        Log.d(TAG, message);
    }

    public static void t(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void s(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
