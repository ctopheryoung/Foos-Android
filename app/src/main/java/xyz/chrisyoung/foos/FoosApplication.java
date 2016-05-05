package xyz.chrisyoung.foos;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by topher on 5/4/16.
 */
public class FoosApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
