package sg.vinova.aki.database.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import sg.vinova.aki.database.model.database.DatabaseHelper;
import sg.vinova.aki.database.model.DataInteractor;
import sg.vinova.aki.database.model.SampleData;
import sg.vinova.aki.database.view.MainView;

/**
 * Created by Aki on 6/22/2017.
 */

public class MainPresenterImpl implements MainPresenter, DataInteractor.onGetListFinishedListener {
    private MainView mainView;
    private DatabaseHelper databaseHelper;
    private DataInteractor dataInteractor;

    public MainPresenterImpl(MainView mainView, DatabaseHelper databaseHelper) {
        this.mainView = mainView;
        this.databaseHelper = databaseHelper;
        dataInteractor = new DataInteractor();
    }

    @Override
    public void onResume() {

        if (isNetworkAvailable(mainView.getContext())) {
            // Load images from Internet
            dataInteractor.getList(this);
        } else {
            // Load images from Database
            load();
        }
    }

//    @Override
//    public void save(SampleData clickedItem) {
//        // Save clicked image into database
//        if (isNetworkAvailable(mainView.getContext())) {
//            try {
//                Dao<SampleData, Integer> simpleDao = databaseHelper.getSimpleDataDao();
//                simpleDao.create(clickedItem);
//                mainView.saveCompleted(clickedItem.getImageName());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void load() {
        // Load images from database
        try {
            Dao<SampleData, Integer> simpleDao = databaseHelper.getSimpleDataDao();
            mainView.loadCompleted(simpleDao.queryForAll(), isNetworkAvailable(mainView.getContext()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetListFinished(List<SampleData> list) {
        mainView.downloadCompleted(list, isNetworkAvailable(mainView.getContext()));
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        return (info != null &&
                (info.getType() == ConnectivityManager.TYPE_WIFI ||
                        info.getType() == ConnectivityManager.TYPE_MOBILE));
    }
}
