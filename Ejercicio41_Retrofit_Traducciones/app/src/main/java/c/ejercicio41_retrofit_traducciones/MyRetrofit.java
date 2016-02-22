package c.ejercicio41_retrofit_traducciones;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cristina on 22/02/2016.
 */
public class MyRetrofit {

    public interface MyRetrofitInterface {
        @GET("translate")
        Call<String> getTraduccion(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
    }

    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    private static MyRetrofitInterface servicio;
    private static MyRetrofit myRetrofitInstance;

    private MyRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        servicio = retrofit.create(MyRetrofitInterface.class);
    }

    public static synchronized MyRetrofit getMyRetrofitInstance() {
        if (myRetrofitInstance == null)
            myRetrofitInstance = new MyRetrofit();

        return myRetrofitInstance;
    }

    public static MyRetrofitInterface getServicio() {
        return servicio;
    }
}
