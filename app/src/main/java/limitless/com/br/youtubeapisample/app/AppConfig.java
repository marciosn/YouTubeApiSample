package limitless.com.br.youtubeapisample.app;

/**
 * Created by Márcio Sn on 23/12/2015.
 */
public class AppConfig {

    public AppConfig() {
    }

    public static final String RESULT = "RESULT";
    public static final String YOUTUBE_API_KEY = "AIzaSyBE8-6Mm2nXjDfNSJMF88EgN_vacB4thag";
    public static final String YOUTUBE_CHANNEL_ID = "UCuVIWETFdxzwlHEHMbhm2_w";
    public static final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search?key="+ YOUTUBE_API_KEY +"&channelId="+ YOUTUBE_CHANNEL_ID +"&part=snippet,id&order=date&maxResults=50";


    public static final String YOUTUBE_API_URL_FULL = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBE8-6Mm2nXjDfNSJMF88EgN_vacB4thag&channelId=UCuVIWETFdxzwlHEHMbhm2_w&part=snippet,id&order=date&maxResults=50";
}
