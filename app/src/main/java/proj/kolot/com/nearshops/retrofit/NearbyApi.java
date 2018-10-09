package proj.kolot.com.nearshops.retrofit;


import proj.kolot.com.nearshops.data.GeneralResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NearbyApi {

    @GET("maps/api/place/nearbysearch/json")
    Call<GeneralResponse> getNearByPlaces(@Query("location") String latLong, @Query("radius") int radius, @Query("type") String type, @Query("key") String key);


}
