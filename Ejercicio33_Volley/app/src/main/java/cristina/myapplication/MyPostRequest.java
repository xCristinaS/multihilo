package cristina.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Cristina on 27/01/2016.
 */
public class MyPostRequest extends StringRequest {

    Map<String, String> params;

    public MyPostRequest(String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, url, listener, errorListener);
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
