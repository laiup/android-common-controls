package co.laiup.adr.laiuplib;

import android.app.Application;
import android.content.Context;

/**
 * Project Laiup Lib
 * Created by Ha on 10/2/2015.
 */
public class App extends Application{
    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        instance = this;
    }

    public static Application getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }

    public static Context getAppContext() {
        if(instance == null) {
            instance = new App();
        }
        return instance.getApplicationContext();
    }
}
