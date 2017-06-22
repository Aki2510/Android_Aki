package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import layout.Deliveries;
import model.Delivery;
import sg.vinova.aki.myapplication.R;

/**
 * Created by Aki on 6/14/2017.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private Context context;
    private List<Delivery> deliveries;
    private int count;
    private static RecyclerViewOnClickListener myOnClickListener;

    public ExploreAdapter(Context context, List<Delivery> deliveries, RecyclerViewOnClickListener myOnClickListener) {
        this.context = context;
        this.deliveries = deliveries;
        this.count = deliveries.size();
        this.myOnClickListener = myOnClickListener;
    }

    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.ViewHolder holder, int position) {
        holder.tvCategory.setText(deliveries.get(2 * position).getTvCategory());

        holder.tvName.setText(deliveries.get(2 * position).getTvName());
        holder.tvCountry.setText(deliveries.get(2 * position).getTvCountry());
        Glide.with(context).load(deliveries.get(2 * position).getIvImage()).into(holder.ivImage);
        holder.tvPriceRange.setText(deliveries.get(2 * position).getTvPriceRange());

        count -= 2;
        if (count <= 0) return;

        holder.tvName2.setText(deliveries.get(2 * position + 1).getTvName());
        holder.tvCountry2.setText(deliveries.get(2 * position + 1).getTvCountry());
        Glide.with(context).load(deliveries.get(2 * position + 1).getIvImage()).into(holder.ivImage2);
        holder.tvPriceRange2.setText(deliveries.get(2 * position + 1).getTvPriceRange());
    }

    @Override
    public int getItemCount() {
        return (deliveries.size() + 1) / 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCategory;
        TextView tvName;
        TextView tvCountry;
        ImageView ivImage;
        TextView tvPriceRange;
        TextView tvName2;
        TextView tvCountry2;
        ImageView ivImage2;
        TextView tvPriceRange2;
        Button btnMore;

        public ViewHolder(View v) {
            super(v);
            tvCategory = (TextView) v.findViewById(R.id.explore_food_category);
            tvName = (TextView) v.findViewById(R.id.explore_food_name);
            tvCountry = (TextView) v.findViewById(R.id.explore_food_country);
            ivImage = (ImageView) v.findViewById(R.id.explore_food_image);
            tvPriceRange = (TextView) v.findViewById(R.id.explore_food_pricerange);
            btnMore = (Button) v.findViewById(R.id.explore_more_button);

            tvName2 = (TextView) v.findViewById(R.id.explore_food_name2);
            tvCountry2 = (TextView) v.findViewById(R.id.explore_food_country2);
            ivImage2 = (ImageView) v.findViewById(R.id.explore_food_image2);
            tvPriceRange2 = (TextView) v.findViewById(R.id.explore_food_pricerange2);

            btnMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myOnClickListener.onClickListener(v, getAdapterPosition());
        }
    }

    public interface RecyclerViewOnClickListener {
        void onClickListener(View v, int position);
    }
}
