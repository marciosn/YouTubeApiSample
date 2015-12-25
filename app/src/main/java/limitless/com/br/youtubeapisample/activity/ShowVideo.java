package limitless.com.br.youtubeapisample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import limitless.com.br.youtubeapisample.R;
import limitless.com.br.youtubeapisample.app.AppConfig;
import limitless.com.br.youtubeapisample.app.AppController;
import limitless.com.br.youtubeapisample.model.YouTubeVideo;

public class ShowVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubeVideo youTubeVideo;
    public NetworkImageView imageView;
    private YouTubePlayerView youtube;
    private String videoId;

    private TextView title;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageView = (NetworkImageView) findViewById(R.id.thumbnail_toolbar_layout);

        title = (TextView) findViewById(R.id.title_video);
        description = (TextView) findViewById(R.id.description_video);
        date = (TextView) findViewById(R.id.date_video);

        youtube = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youtube.initialize(AppConfig.YOUTUBE_API_KEY, this);

        Intent intent = getIntent();
        youTubeVideo = intent.getParcelableExtra(AppConfig.RESULT);

        if (youTubeVideo != null) {

            imageView.setImageUrl(youTubeVideo.getThumbnailHigh(), imageLoader);
            videoId = youTubeVideo.getVideoId();
            toolbar.setTitle(youTubeVideo.getChannelTitle());

            title.setText(youTubeVideo.getTitle());
            description.setText(youTubeVideo.getDescription());
            date.setText(youTubeVideo.getPublishedAt());
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean loadAgain) {
        if(!loadAgain){
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "onInitializationFailure()", Toast.LENGTH_SHORT).show();
    }
}
