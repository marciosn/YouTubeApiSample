package limitless.com.br.youtubeapisample.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import limitless.com.br.youtubeapisample.model.YouTubeVideo;

/**
 * Created by Márcio Sn on 24/12/2015.
 */
public class ConvertJsonToObjects {

    private static final String TAG = ConvertJsonToObjects.class.getSimpleName();
    private List<YouTubeVideo> youTubeVideos;

    public ConvertJsonToObjects() {
        this.youTubeVideos = new ArrayList<>();
    }

    public List<YouTubeVideo> convert(JSONArray items){

        try {
            for(int i = 0 ; i < items.length(); i++){

                //get json objects

                JSONObject item = items.getJSONObject(i);
                JSONObject id = item.getJSONObject("id");
                JSONObject snippet = item.getJSONObject("snippet");
                JSONObject thumb = snippet.getJSONObject("thumbnails");
                JSONObject default_thumb = thumb.getJSONObject("default");
                JSONObject medium_thumb = thumb.getJSONObject("medium");
                JSONObject high_thumb = thumb.getJSONObject("high");

                //get string values from json objects

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

                youTubeVideos.add(youTubeVideo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }

        return  youTubeVideos;
    }
}
