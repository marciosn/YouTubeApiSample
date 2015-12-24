package limitless.com.br.youtubeapisample.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Márcio Sn on 24/12/2015.
 */
public class SearchInfo implements Parcelable{

    private String kind;
    private String etag;
    private String nextPageToken;
    private int totalResults;
    private int resultsPerPage;
    private List<YouTubeVideo> youTubeVideos;

    protected SearchInfo() {
        kind = "";
        etag = "";
        nextPageToken = "";
        totalResults = 0;
        resultsPerPage = 0;
        this.youTubeVideos = Collections.emptyList();
    }

    protected SearchInfo(Parcel in) {
        kind = in.readString();
        etag = in.readString();
        nextPageToken = in.readString();
        totalResults = in.readInt();
        resultsPerPage = in.readInt();
        youTubeVideos = in.createTypedArrayList(YouTubeVideo.CREATOR);
    }

    public static final Creator<SearchInfo> CREATOR = new Creator<SearchInfo>() {
        @Override
        public SearchInfo createFromParcel(Parcel in) {
            return new SearchInfo(in);
        }

        @Override
        public SearchInfo[] newArray(int size) {
            return new SearchInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(etag);
        dest.writeString(nextPageToken);
        dest.writeInt(totalResults);
        dest.writeInt(resultsPerPage);
        dest.writeTypedList(youTubeVideos);
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public List<YouTubeVideo> getYouTubeVideos() {
        return youTubeVideos;
    }

    public void setYouTubeVideos(List<YouTubeVideo> youTubeVideos) {
        this.youTubeVideos = youTubeVideos;
    }
}
