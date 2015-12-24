package limitless.com.br.youtubeapisample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class YouTubeVideo implements Parcelable{
	private String id;
	private String videoId;
	private String title;
	private String thumbnailDefault;
	private String thumbnailMedium;
	private String thumbnailHigh;
	private String description;
	private String publishedAt;
	private String channelTitle;

    public YouTubeVideo() {
        this.id = "";
        this.videoId = "";
        this.title = "";
        this.thumbnailDefault = "";
        this.thumbnailMedium = "";
        this.thumbnailHigh = "";
        this.description = "";
        this.publishedAt = "";
        this.channelTitle = "";
    }

    protected YouTubeVideo(Parcel in) {
        id = in.readString();
        videoId = in.readString();
        title = in.readString();
        thumbnailDefault = in.readString();
        thumbnailMedium = in.readString();
        thumbnailHigh = in.readString();
        description = in.readString();
        publishedAt = in.readString();
        channelTitle = in.readString();
    }

    public static final Creator<YouTubeVideo> CREATOR = new Creator<YouTubeVideo>() {
        @Override
        public YouTubeVideo createFromParcel(Parcel in) {
            return new YouTubeVideo(in);
        }

        @Override
        public YouTubeVideo[] newArray(int size) {
            return new YouTubeVideo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(videoId);
        dest.writeString(title);
        dest.writeString(thumbnailDefault);
        dest.writeString(thumbnailMedium);
        dest.writeString(thumbnailHigh);
        dest.writeString(description);
        dest.writeString(publishedAt);
        dest.writeString(channelTitle);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailDefault() {
        return thumbnailDefault;
    }

    public void setThumbnailDefault(String thumbnailDefault) {
        this.thumbnailDefault = thumbnailDefault;
    }

    public String getThumbnailMedium() {
        return thumbnailMedium;
    }

    public void setThumbnailMedium(String thumbnailMedium) {
        this.thumbnailMedium = thumbnailMedium;
    }

    public String getThumbnailHigh() {
        return thumbnailHigh;
    }

    public void setThumbnailHigh(String thumbnailHigh) {
        this.thumbnailHigh = thumbnailHigh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
}
