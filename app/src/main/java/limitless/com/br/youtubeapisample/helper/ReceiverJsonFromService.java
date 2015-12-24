package limitless.com.br.youtubeapisample.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import limitless.com.br.youtubeapisample.app.AppConfig;
import limitless.com.br.youtubeapisample.model.SearchResult;

/**
 * Created by Márcio Sn on 24/12/2015.
 */
public class ReceiverJsonFromService {

    private static final String TAG = ReceiverJsonFromService.class.getSimpleName();
    private Context context;
    private RequestQueue requestQueue;
    private String URL = AppConfig.YOUTUBE_API_URL;
    private ConvertJsonToObjects convertJsonToObjects;
    private SearchResult searchResult;

    public ReceiverJsonFromService(Context context, RequestQueue requestQueue) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.convertJsonToObjects = new ConvertJsonToObjects();
    }

    public SearchResult retriveData() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, response.toString());

                try {
                    searchResult = convertJsonToObjects.convert(response);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        int timeout = 100000;
        RetryPolicy policy = new DefaultRetryPolicy(timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        jsObjRequest.setTag(TAG);
        requestQueue.add(jsObjRequest);

        return searchResult;
    }
}
