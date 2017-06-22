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

import model.Delivery;
import sg.vinova.aki.myapplication.R;

/**
 * Created by Aki on 6/13/2017.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {
    private List<Delivery> deliveries;
    private Context context;
    public DeliveryAdapter(Context context, List<Delivery> deliveries) {
        this.context = context;
        this.deliveries = deliveries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deliveries_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(deliveries.get(position).getTvName());
        holder.tvAddress.setText(deliveries.get(position).getTvAddress());
        holder.tvCountry.setText(deliveries.get(position).getTvCountry());
        holder.tvPriceRange.setText(deliveries.get(position).getTvPriceRange());
        Glide.with(context).load(deliveries.get(position).getIvImage()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvAddress;
        TextView tvCountry;
        TextView tvPriceRange;
        ImageView ivImage;

        public ViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.deliveries_food_name);
            tvAddress = (TextView) v.findViewById(R.id.deliveries_food_address);
            tvCountry = (TextView) v.findViewById(R.id.deliveries_food_country);
            tvPriceRange = (TextView) v.findViewById(R.id.deliveries_food_pricerange);
            ivImage = (ImageView) v.findViewById(R.id.deliveries_food_image);
        }
    }
}
