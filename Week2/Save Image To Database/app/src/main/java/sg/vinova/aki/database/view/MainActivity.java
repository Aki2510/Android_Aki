package sg.vinova.aki.database.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.facebook.stetho.Stetho;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.util.List;

import sg.vinova.aki.database.view.adapter.MyAdapter;
import sg.vinova.aki.database.R;
import sg.vinova.aki.database.model.SampleData;
import sg.vinova.aki.database.model.database.DatabaseHelper;
import sg.vinova.aki.database.presenter.MainPresenter;
import sg.vinova.aki.database.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView {
    private DatabaseHelper databaseHelper = null;
    RecyclerView recyclerView;
    MyAdapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter(this, getHelper());
        recyclerView.setAdapter(adapter);

        mainPresenter = new MainPresenterImpl(this, getHelper());
    }

    @Override
    public void downloadCompleted(List<SampleData> list, boolean isNetworkAvailable) {
        adapter.setList(list, isNetworkAvailable);
    }

    @Override
    public void loadCompleted(List<SampleData> list, boolean isNetworkAvailable) {
        adapter.setList(list, isNetworkAvailable);
    }
//
//    @Override
//    public void onClickListener(View v, SampleData itemClicked) {
//        mainPresenter.save(itemClicked);
//    }


    @Override
    protected void onResume() {
        mainPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


    /**
     * You'll need this in your class to get the helper from the manager once per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
