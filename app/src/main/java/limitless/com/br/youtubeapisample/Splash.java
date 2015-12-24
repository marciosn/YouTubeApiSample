package limitless.com.br.youtubeapisample;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import limitless.com.br.youtubeapisample.app.AppConfig;
import limitless.com.br.youtubeapisample.helper.ConvertJsonToObjects;
import limitless.com.br.youtubeapisample.model.SearchResult;

public class Splash extends AppCompatActivity {

    private static final String TAG = Splash.class.getSimpleName();
    private RequestQueue requestQueue;
    private String URL = AppConfig.YOUTUBE_API_URL;
    private SearchResult searchResult;
    private ConvertJsonToObjects convertJsonToObjects;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestQueue = Volley.newRequestQueue(Splash.this);

        try {
            convertJsonToObjects = new ConvertJsonToObjects();
            retriveData();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }

    public SearchResult retriveData() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                //Log.d(TAG, response.toString());

                searchResult = convertJsonToObjects.convert(response);
                Log.d(TAG, searchResult.toString());

                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.putExtra(AppConfig.RESULT, searchResult);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                hidePDialog();
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

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
