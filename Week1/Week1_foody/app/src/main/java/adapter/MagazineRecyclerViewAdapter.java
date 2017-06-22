package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import model.MagazineArticle;
import sg.vinova.aki.week1_foody.R;

/**
 * Created by Aki on 6/19/2017.
 */

public class MagazineRecyclerViewAdapter extends RecyclerView.Adapter<MagazineRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<MagazineArticle> articles;

    public MagazineRecyclerViewAdapter(Context context, List<MagazineArticle> articles){
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.magazine_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(articles.get(position).getArticleImage()).into(holder.ivMagazineImage);
        holder.tvMagazineSource.setText(articles.get(position).getArticleSource());
        holder.tvMagazineTitle.setText(articles.get(position).getArticleTitle());
        holder.tvMagazineDate.setText(articles.get(position).getArticleDate());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMagazineImage;
        TextView tvMagazineSource;
        TextView tvMagazineTitle;
        TextView tvMagazineDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMagazineImage = (ImageView) itemView.findViewById(R.id.magazine_image);
            tvMagazineSource = (TextView) itemView.findViewById(R.id.magazine_source);
            tvMagazineTitle = (TextView) itemView.findViewById(R.id.magazine_title);
            tvMagazineDate = (TextView) itemView.findViewById(R.id.magazine_date);
        }
    }
}
