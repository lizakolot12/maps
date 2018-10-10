package proj.kolot.com.nearshops;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import proj.kolot.com.nearshops.retrofit.NearbyApi;
import proj.kolot.com.nearshops.retrofit.RetrofitClientInstance;

public class App extends Application {
    private static App instance;
    private NearbyApi nearbyApi;



    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers(), new Crashlytics());
        instance = this;
        nearbyApi = RetrofitClientInstance.getRetrofitInstance().create(NearbyApi.class);

    }

    public NearbyApi getNearbyApi() {
        return nearbyApi;
    }

    public static App getInstance() {
        return instance;
    }



}
