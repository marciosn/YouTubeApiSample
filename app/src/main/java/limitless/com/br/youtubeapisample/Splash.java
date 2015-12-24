package limitless.com.br.youtubeapisample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import limitless.com.br.youtubeapisample.helper.ConvertJsonToObjects;
import limitless.com.br.youtubeapisample.model.SearchResult;

public class Splash extends AppCompatActivity {

    private static final String TAG = Splash.class.getSimpleName();
    private AppConfig appConfig;
    private RequestQueue requestQueue;
    private String URL = appConfig.YOUTUBE_API_URL;
    private SearchResult searchResult;
    private ConvertJsonToObjects convertJsonToObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            convertJsonToObjects = new ConvertJsonToObjects();
            retriveData();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }
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

                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.putExtra(appConfig.RESULT, searchResult);
                startActivity(intent);
                finish();

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
