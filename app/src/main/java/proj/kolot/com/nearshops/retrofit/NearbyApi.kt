package proj.kolot.com.nearshops.retrofit

import proj.kolot.com.nearshops.data.GeneralResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyApi {

    @GET("maps/api/place/nearbysearch/json")
    fun getNearByPlaces(@Query("location") latLong: String, @Query("radius") radius: Int, @Query("type") type: String, @Query("key") key: String): Call<GeneralResponse>


}
