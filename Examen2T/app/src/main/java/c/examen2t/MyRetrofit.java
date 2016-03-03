package c.examen2t;

import java.io.IOException;

import c.examen2t.pojos.Respuesta;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cristina on 03/03/2016.
 */
public class MyRetrofit {

    private final String KEY = "fdf86e05600a69e7a4b10b13f8cc04a1";

    public interface MyRetrofitInterface {
        @GET("weather")
        Call<Respuesta> getTraduccion(@Query("q") String localidad, @Query("units") String units, @Query("lang") String lang);
    }

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static MyRetrofitInterface servicio;
    private static MyRetrofit myRetrofitInstance;

    private MyRetrofit() {
        // Para que se muestre el log de todas las peticiones.
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Se crea el cliente OkHttp.
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        // Se le añade un interceptador.
        b.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                // Se obtiene la petición original.
                Request request = chain.request();
                // Se obtiene una nueva URL basada en la original de la petición
                // agregando el parámetro de consulta correspondiente a la api key.
                HttpUrl newUrl = request.url().newBuilder().addQueryParameter("appid", KEY).build();
                // Se crea una nueva petición basándose en la original.
                // Se cambia la url por la nueva modificada y se agrega una cabecera.
                Request newRequest;
                newRequest = request.newBuilder().url(newUrl).build();
                // Se reemplaza la nueva petición por la antigua y se continúa.
                return chain.proceed(newRequest);
            }
        });
        b.addInterceptor(logInterceptor);
        OkHttpClient client = b.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
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
