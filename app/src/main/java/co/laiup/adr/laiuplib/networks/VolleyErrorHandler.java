package co.laiup.adr.laiuplib.networks;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class VolleyErrorHandler {
    // volley error handler
    public static void volleyErrorHandler(Context context, VolleyError error) {
        if(error instanceof TimeoutError) {
            Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show();
        }else if (error instanceof NoConnectionError) {
            Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
        } else if (error instanceof AuthFailureError) {
            Toast.makeText(context, "Auth Error", Toast.LENGTH_SHORT).show();
        } else if (error instanceof ServerError) {
            Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
        } else if (error instanceof NetworkError) {
            Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
        } else if (error instanceof ParseError) {
            Toast.makeText(context, "Parse Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show();
        }
    }
}
