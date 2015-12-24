package limitless.com.br.youtubeapisample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import limitless.com.br.youtubeapisample.R;
import limitless.com.br.youtubeapisample.app.AppController;
import limitless.com.br.youtubeapisample.model.YouTubeVideo;

/**
 * Created by Márcio Sn on 22/12/2015.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<YouTubeVideo> itens;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView date;
        public NetworkImageView imageView;

        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
            date = (TextView) v.findViewById(R.id.date);
            imageView = (NetworkImageView) v.findViewById(R.id.thumbnail);
        }
    }

    public Adapter(List<YouTubeVideo> itens) {
        this.itens = itens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        YouTubeVideo video = itens.get(position);
        holder.title.setText(video.getTitle());
        holder.description.setText(video.getDescription());
        holder.date.setText(video.getPublishedAt());
        holder.imageView.setImageUrl(video.getThumbnailMedium(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
