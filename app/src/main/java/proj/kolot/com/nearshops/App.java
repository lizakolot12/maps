package proj.kolot.com.nearshops;

import android.app.Application;

import proj.kolot.com.nearshops.retrofit.NearbyApi;
import proj.kolot.com.nearshops.retrofit.RetrofitClientInstance;

public class App extends Application {
    private static App instance;
    private NearbyApi nearbyApi;



    @Override
    public void onCreate() {
        super.onCreate();
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
