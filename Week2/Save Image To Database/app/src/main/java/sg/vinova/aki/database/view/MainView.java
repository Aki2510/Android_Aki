package sg.vinova.aki.database.view;

import android.content.Context;

import java.util.List;

import sg.vinova.aki.database.model.SampleData;

/**
 * Created by Aki on 6/22/2017.
 */

public interface MainView {
    Context getContext();
    void downloadCompleted(List<SampleData> list, boolean isNetworkAvailable);
    void loadCompleted(List<SampleData> list, boolean isNetworkAvailable);
}
