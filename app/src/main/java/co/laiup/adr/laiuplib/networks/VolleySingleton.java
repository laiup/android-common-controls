package co.laiup.adr.laiuplib.networks;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import co.laiup.adr.laiuplib.App;

/**
 * Created by Ha on 10/5/2015.
 */
public class VolleySingleton {
    public static VolleySingleton instance = null;
    private RequestQueue requestQueue;

    public VolleySingleton() {
        requestQueue = Volley.newRequestQueue(App.getAppContext());
    }

    public static VolleySingleton getInstance() {
        if(instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
