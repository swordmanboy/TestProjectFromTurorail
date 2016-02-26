package thailand.transport.center.smbz.com.myapp4.remote;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import thailand.transport.center.smbz.com.myapp4.data.model.Weather;

/**
 * Created by apinun.w on 24/2/2559.
 */
public interface WeatherAPI {
//    https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bangkok%2C%20th%22%20)%20and%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
    String BASE_URL = "https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bangkok%2C%20th%22%20)%20and%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();

    class Factory{
        public static WeatherAPI service;
        public static WeatherAPI getInstance(){
            if(service == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service  = retrofit.create(WeatherAPI.class);
//                return service;
            }
            return service;
        }
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build();

    WeatherAPI service = retrofit.create(WeatherAPI.class);
}
