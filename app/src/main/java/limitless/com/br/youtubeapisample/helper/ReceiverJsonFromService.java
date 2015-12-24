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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import limitless.com.br.youtubeapisample.app.AppConfig;
import limitless.com.br.youtubeapisample.model.YouTubeVideo;

/**
 * Created by Márcio Sn on 24/12/2015.
 */
public class ReceiverJsonFromService {

    private static final String TAG = ReceiverJsonFromService.class.getSimpleName();
    private AppConfig appConfig;
    private Context context;
    private RequestQueue requestQueue;
    private List<YouTubeVideo> videos;

    private String URL = appConfig.YOUTUBE_API_URL;

    public ReceiverJsonFromService(Context context, RequestQueue requestQueue) {
        this.context = context;
        this.videos = new ArrayList<>();
        this.requestQueue = requestQueue;
    }

    public void retriveJSON() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d("DEBUG", response.toString());

                try {

                    JSONArray items = response.getJSONArray("items");
                    Log.d("DEBUG", "ARRAY ITEMS " + items);

                    for(int i = 0 ; i < items.length(); i++){
                        JSONObject item = items.getJSONObject(i);
                        JSONObject id = item.getJSONObject("id");
                        JSONObject snippet = item.getJSONObject("snippet");
                        JSONObject thumb = snippet.getJSONObject("thumbnails");
                        JSONObject default_thumb = thumb.getJSONObject("default");
                        JSONObject medium_thumb = thumb.getJSONObject("medium");
                        JSONObject high_thumb = thumb.getJSONObject("high");

                        String videoId = id.getString("videoId");
                        String title = snippet.getString("title");
                        String thumb_default = default_thumb.getString("url");
                        String thumb_medium = medium_thumb.getString("url");
                        String thumb_high = high_thumb.getString("url");
                        String description = snippet.getString("description");
                        String publishedAt = snippet.getString("publishedAt");
                        String channelTitle = snippet.getString("channelTitle");

                        YouTubeVideo youTubeVideo = new YouTubeVideo();

                        youTubeVideo.setId(String.valueOf(i));
                        youTubeVideo.setVideoId(videoId);
                        youTubeVideo.setTitle(title);
                        youTubeVideo.setThumbnailDefault(thumb_default);
                        youTubeVideo.setThumbnailMedium(thumb_medium);
                        youTubeVideo.setThumbnailHigh(thumb_high);
                        youTubeVideo.setDescription(description);
                        youTubeVideo.setPublishedAt(publishedAt);
                        youTubeVideo.setChannelTitle(channelTitle);

                        videos.add(youTubeVideo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ERROR", e.getMessage());
                }

                Log.d("DEBUG", "Tamanho " + videos.size());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());

            }
        });

        int timeout = 100000;
        RetryPolicy policy = new DefaultRetryPolicy(timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        jsObjRequest.setTag(TAG);
        requestQueue.add(jsObjRequest);
    }
}
