package c.ejercicio39_serviceapitimezonedb;

import android.app.Application;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cristina on 08/02/2016.
 */
public class MyRetrofit extends Application {

    public interface MyRetrofitInterface {
        /*
        @GET("?zone=America/Toronto&format=json&key=F3RG9IK3NE9K")
        Call<MyTimeZone> getHora();
        */

        @GET("?format=json&key=F3RG9IK3NE9K")
        Call<MyTimeZone> getHora(@Query("zone") String zona);
    }

    private static final String BASE_URL = "http://api.timezonedb.com/";
    private static MyRetrofitInterface servicio;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        servicio = retrofit.create(MyRetrofitInterface.class);
    }

    public static MyRetrofitInterface getServicio(){
        return servicio;
    }
}
