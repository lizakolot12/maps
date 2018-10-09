package proj.kolot.com.nearshops.retrofit;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL ="https://maps.googleapis.com";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(testchangeresponse())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient testchangeresponse() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();



                Response response = chain.proceed(original);

                response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), rawJson)).build();

                return response;
            }
        });

       return httpClient.build();
    }

   static private String rawJson = "{\n" +
           "   \"html_attributions\" : [],\n" +
           "   \"results\" : [\n" +
           "      {\n" +
           "         \"geometry\" : {\n" +
           "            \"location\" : {\n" +
           "               \"lat\" : 48.4578467,\n" +
           "               \"lng\" : 35.0597139\n" +
           "            },\n" +
           "            \"viewport\" : {\n" +
           "               \"northeast\" : {\n" +
           "                  \"lat\" : 48.4591064302915,\n" +
           "                  \"lng\" : 35.0609613802915\n" +
           "               },\n" +
           "               \"southwest\" : {\n" +
           "                  \"lat\" : 48.45640846970851,\n" +
           "                  \"lng\" : 35.0582634197085\n" +
           "               }\n" +
           "            }\n" +
           "         },\n" +
           "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png\",\n" +
           "         \"id\" : \"0adb07df4a3e8638d69a8b4125afdaf770090ee6\",\n" +
           "         \"name\" : \"Atrium\",\n" +
           "         \"opening_hours\" : {\n" +
           "            \"open_now\" : true\n" +
           "         },\n" +
           "         \"photos\" : [\n" +
           "            {\n" +
           "               \"height\" : 4000,\n" +
           "               \"html_attributions\" : [\n" +
           "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/111235969193465509349/photos\\\"\\u003eSuper Mural\\u003c/a\\u003e\"\n" +
           "               ],\n" +
           "               \"photo_reference\" : \"CmRaAAAApzz-Bfuns-B44VuT1HWPQCcWIDfMGceiM1s1eEpIC3O9Xysmuwsn8T1K7TsSIos7lZ1ov_uad7KioAOoPjTYNBij4GUXXoHCQ6WBzEOQPpavzSoB9gHUHRyjB6FbDIvWEhDpQVsnReaNC1x0zANztHTtGhQZGcAmAvyP7jY11EDaOEuRUBzXZg\",\n" +
           "               \"width\" : 3000\n" +
           "            }\n" +
           "         ],\n" +
           "         \"place_id\" : \"ChIJV4grLsrj20ARChZlKtCn2-c\",\n" +
           "         \"plus_code\" : {\n" +
           "            \"compound_code\" : \"F355+4V Dnipro, Dnipropetrovsk Oblast, Ukraine\",\n" +
           "            \"global_code\" : \"8GWQF355+4V\"\n" +
           "         },\n" +
           "         \"rating\" : 5,\n" +
           "         \"reference\" : \"ChIJV4grLsrj20ARChZlKtCn2-c\",\n" +
           "         \"scope\" : \"GOOGLE\",\n" +
           "         \"types\" : [ \"shopping_mall\", \"point_of_interest\", \"establishment\" ],\n" +
           "         \"vicinity\" : \"Dmytra Yavornytskoho Avenue, 22-26, Dnipro\"\n" +
           "      },\n" +
           "      {\n" +
           "         \"geometry\" : {\n" +
           "            \"location\" : {\n" +
           "               \"lat\" : 48.456384,\n" +
           "               \"lng\" : 35.0608399\n" +
           "            },\n" +
           "            \"viewport\" : {\n" +
           "               \"northeast\" : {\n" +
           "                  \"lat\" : 48.4577176802915,\n" +
           "                  \"lng\" : 35.0621713802915\n" +
           "               },\n" +
           "               \"southwest\" : {\n" +
           "                  \"lat\" : 48.4550197197085,\n" +
           "                  \"lng\" : 35.0594734197085\n" +
           "               }\n" +
           "            }\n" +
           "         },\n" +
           "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png\",\n" +
           "         \"id\" : \"c18ce618f290ddbfea64753f19e37acb87330aa7\",\n" +
           "         \"name\" : \"Kasta Fashion\",\n" +
           "         \"opening_hours\" : {\n" +
           "            \"open_now\" : true\n" +
           "         },\n" +
           "         \"photos\" : [\n" +
           "            {\n" +
           "               \"height\" : 2048,\n" +
           "               \"html_attributions\" : [\n" +
           "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/102490306457675409868/photos\\\"\\u003eArtem Sin\\u003c/a\\u003e\"\n" +
           "               ],\n" +
           "               \"photo_reference\" : \"CmRZAAAAHUYghqQUdfmVW55H7NkyUNpu05PDTtWLRQGrVkTtr2qsIOB_EILcx_FMGh8X4GJn-5FDZzUhL8-dByIwAq7TjLErKhIftlJrgIkjzTyMPTbT3A9_pOgbhzIFZpFYVDYKEhAio1oZgDlvMUZXLKUCG5CjGhRZBJy1h99KkxR7OuN6gmFWPAGbDg\",\n" +
           "               \"width\" : 1536\n" +
           "            }\n" +
           "         ],\n" +
           "         \"place_id\" : \"ChIJs3lf1dDi20ARsZHN0wTmfHk\",\n" +
           "         \"plus_code\" : {\n" +
           "            \"compound_code\" : \"F346+H8 Dnipro, Dnipropetrovsk Oblast, Ukraine\",\n" +
           "            \"global_code\" : \"8GWQF346+H8\"\n" +
           "         },\n" +
           "         \"reference\" : \"ChIJs3lf1dDi20ARsZHN0wTmfHk\",\n" +
           "         \"scope\" : \"GOOGLE\",\n" +
           "         \"types\" : [ \"shopping_mall\", \"point_of_interest\", \"establishment\" ],\n" +
           "         \"vicinity\" : \"Dmytra Yavornytskoho Avenue, 22, Dnipro\"\n" +
           "      }\n" +
           "   ],\n" +
           "   \"status\" : \"OK\"\n" +
           "}";
}