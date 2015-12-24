package limitless.com.br.youtubeapisample.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Márcio Sn on 24/12/2015.
 */
public class SearchResult implements Parcelable{

    private String kind;
    private String etag;
    private String nextPageToken;
    private int totalResults;
    private int resultsPerPage;
    private List<YouTubeVideo> youTubeVideos;

    public SearchResult() {
        kind = "";
        etag = "";
        nextPageToken = "";
        totalResults = 0;
        resultsPerPage = 0;
        this.youTubeVideos = Collections.emptyList();
    }

    protected SearchResult(Parcel in) {
        kind = in.readString();
        etag = in.readString();
        nextPageToken = in.readString();
        totalResults = in.readInt();
        resultsPerPage = in.readInt();
        youTubeVideos = in.createTypedArrayList(YouTubeVideo.CREATOR);
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
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

    @Override
    public String toString() {
        return "SearchResult{" +
                "kind='" + kind + '\'' +
                ", etag='" + etag + '\'' +
                ", nextPageToken='" + nextPageToken + '\'' +
                ", totalResults=" + totalResults +
                ", resultsPerPage=" + resultsPerPage +
                '}';
    }
}
