package sg.vinova.aki.database.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sg.vinova.aki.database.R;
import sg.vinova.aki.database.model.SampleData;
import sg.vinova.aki.database.model.database.DatabaseHelper;

/**
 * Created by Aki on 6/22/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<SampleData> list;
    private DatabaseHelper databaseHelper;
    private boolean isNetworkAvailable;

    public MyAdapter(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        list = new ArrayList<>();
        this.databaseHelper = databaseHelper;
        isNetworkAvailable = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String image = list.get(position).getImage();
        final String imageName = list.get(position).getImageName();

        if (isNetworkAvailable) {
            Glide.with(context)
                    .asBitmap()
                    .load(image)
                    .into(new SimpleTarget<Bitmap>(1600,1600) {
                        @Override
                        public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
                            holder.ivImage.setImageBitmap(resource);

                            new AsyncTask<Void, Void, Void>() {
                                @Override
                                protected Void doInBackground(Void... params) {
                                    boolean isImageAlreadyStored = true;
                                    try {
                                        File file = new File(context.getFilesDir(), imageName);
                                        if (!file.exists()) {
                                            isImageAlreadyStored = false;
                                            FileOutputStream outputStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
                                            resource.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                                            outputStream.close();
                                            Log.d("Image", "File saved: " + context.getFilesDir().getPath() + imageName);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    if (!isImageAlreadyStored) {
                                        try {
                                            Dao<SampleData, Integer> simpleDao = databaseHelper.getSimpleDataDao();
                                            simpleDao.create(new SampleData(image, imageName, context.getFilesDir().getPath()));
                                            Log.d("Image", "File stored: " + context.getFilesDir().getPath() + imageName);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);
                                }
                            }.execute();


                        }
                    });
            Log.d("Image", "load from internet");
        } else {
            Glide.with(context)
                    .load(Uri.fromFile(new File(list.get(position).getImagePath() + "/" + imageName)))
                    .into(holder.ivImage);

            Log.d("Image", "load from db");
        }

        holder.tvImageName.setText(list.get(position).getImageName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvImageName;

        public ViewHolder(View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.image);
            tvImageName = (TextView) itemView.findViewById(R.id.image_name);
        }

//        @Override
//        public void onClick(View v) {
//            myOnClickListener.onClickListener(v, list.get(getAdapterPosition()));
//        }
    }

//    public interface RecyclerViewOnClickListener {
//        void onClickListener(View v, SampleData itemClicked);
//    }

    public void setList(List<SampleData> list, boolean isNetworkAvailable) {
        this.list = list;
        notifyDataSetChanged();
        this.isNetworkAvailable = isNetworkAvailable;
    }
}
