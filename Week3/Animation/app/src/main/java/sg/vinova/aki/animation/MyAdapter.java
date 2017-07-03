package sg.vinova.aki.animation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Aki on 6/27/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<SampleData> list;
    onItemClickListener listener;

    public MyAdapter(Context context, List<SampleData> list, onItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_row_item, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.ivImage);

        holder.tvTitle.setText(list.get(position).getTitle());

        holder.ivImage.setTransitionName(position + "_image");
        holder.tvTitle.setTransitionName(position + "_title");

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                listener.onClick(holder.ivImage, holder.tvTitle, list.get(position), position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView ivImage;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.recycler_item_layout);
            ivImage = (ImageView) itemView.findViewById(R.id.recycler_image);
            tvTitle = (TextView) itemView.findViewById(R.id.recycler_title);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(ivImage, tvTitle, list.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
//
//        @Override
//        public void onClick(View v) {
//            ViewCompat.setTransitionName(ivImage, getAdapterPosition() + "_image");
//            ViewCompat.setTransitionName(tvTitle, getAdapterPosition() + "_title");
//
//            listener.onClick(ivImage, tvTitle, list.get(getAdapterPosition()), getAdapterPosition());
//        }
    }

    interface onItemClickListener {
        void onClick(ImageView imageView, TextView textView, SampleData data, int position);
    }
}
