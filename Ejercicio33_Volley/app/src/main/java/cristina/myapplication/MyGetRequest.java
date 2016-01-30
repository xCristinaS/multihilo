package cristina.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Cristina on 27/01/2016.
 */
public class MyGetRequest extends StringRequest {

    public MyGetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
    }
}
