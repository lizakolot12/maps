package proj.kolot.com.nearshops.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL ="https://maps.googleapis.com";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}