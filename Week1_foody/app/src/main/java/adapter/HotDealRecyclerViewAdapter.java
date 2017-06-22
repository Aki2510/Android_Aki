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

import model.HotDeal;
import sg.vinova.aki.week1_foody.R;

/**
 * Created by Aki on 6/19/2017.
 */

public class HotDealRecyclerViewAdapter extends RecyclerView.Adapter<HotDealRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<HotDeal> deals;

    public HotDealRecyclerViewAdapter(Context context, List<HotDeal> deals) {
        this.context = context;
        this.deals = deals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.select_suggest_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(deals.get(position).getDealImage()).into(holder.ivDealImage);
        holder.tvDealDate.setText(deals.get(position).getDealDate());
        holder.tvDealDiscount.setText(deals.get(position).getDealDiscount());
        holder.tvDealDiscountDescribe.setText(deals.get(position).getDealDiscountDescribe());
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDealImage;
        TextView tvDealDate;
        TextView tvDealDiscount;
        TextView tvDealDiscountDescribe;

        public ViewHolder(View itemView) {
            super(itemView);
            ivDealImage = (ImageView) itemView.findViewById(R.id.select_suggest_image);
            tvDealDate = (TextView) itemView.findViewById(R.id.select_suggest_date);
            tvDealDiscount = (TextView) itemView.findViewById(R.id.select_suggest_discount);
            tvDealDiscountDescribe = (TextView) itemView.findViewById(R.id.select_suggest_discount_describe);
        }
    }
}
