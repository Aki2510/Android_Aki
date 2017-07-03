package sg.vinova.aki.webservicejsonretrofit.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sg.vinova.aki.webservicejsonretrofit.IconGetter;
import sg.vinova.aki.webservicejsonretrofit.R;
import sg.vinova.aki.webservicejsonretrofit.model.entity.DailyForecast;
import sg.vinova.aki.webservicejsonretrofit.model.entity.Temperature;

/**
 * Created by Aki on 6/30/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<DailyForecast> list;
    //    private int[] paddings;
    private ViewGroup parent;

    public MyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        this.parent = parent;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        final DailyForecast today = list.get(position);
        String datetime = today.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
        int weekday = -1;
        String date = "";
        try {
            Date result = df.parse(datetime);
            Calendar c = Calendar.getInstance();
            c.setTime(result);
            weekday = c.get(Calendar.DAY_OF_WEEK);
            date = new SimpleDateFormat("dd/MM", Locale.ENGLISH).format(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvWeekday.setText(getWeekday(weekday));
        holder.tvDate.setText(date);

        IconGetter iconGetter = new IconGetter(context);
        holder.ivImage.setImageDrawable(iconGetter.getIcon(today.getDay().getIcon()));

        Temperature temperature = today.getTemperature();
        String temp = Math.round(temperature.getMaximum().getValue()) + "\u00B0/" +
                Math.round(temperature.getMinimum().getValue()) + "\u00B0";
        holder.tvTemperatures.setText(temp);

        String precipitationProbability = String.valueOf(today.getDay().getPrecipitationProbability()) + "%";
        holder.tvPrecipitationProbability.setText(precipitationProbability);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(today.getMobileLink()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public MyAdapter setPaddings(int[] paddings) {
//        this.paddings = paddings;
//        notifyDataSetChanged();
//        return this;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeekday;
        TextView tvDate;
        ImageView ivImage;
        TextView tvTemperatures;
        TextView tvPrecipitationProbability;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.getLayoutParams().width = Math.round((parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / 4);

            tvWeekday = (TextView) itemView.findViewById(R.id.recycler_view_item_weekday);
            tvDate = (TextView) itemView.findViewById(R.id.recycler_view_item_date);
            ivImage = (ImageView) itemView.findViewById(R.id.recycler_view_item_image);
            tvTemperatures = (TextView) itemView.findViewById(R.id.recycler_view_item_temperatures);
            tvPrecipitationProbability = (TextView) itemView.findViewById(R.id.recycler_view_item_precipitation_probability);
        }
    }

    private String getWeekday(int i) {
        String weekday;
        switch (i) {
            case 2:
                weekday = "T.2";
                break;
            case 3:
                weekday = "T.3";
                break;
            case 4:
                weekday = "T.4";
                break;
            case 5:
                weekday = "T.5";
                break;
            case 6:
                weekday = "T.6";
                break;
            case 7:
                weekday = "T.7";
                break;
            case 1:
                weekday = "CN";
                break;
            default:
                weekday = "N/A";
                break;
        }
        return weekday;
    }

    public void setList(List<DailyForecast> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<DailyForecast> getList() {
        return list;
    }
}
