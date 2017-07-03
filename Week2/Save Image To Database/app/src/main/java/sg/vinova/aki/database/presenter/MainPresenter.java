package sg.vinova.aki.database.presenter;

import android.content.Context;

import sg.vinova.aki.database.model.SampleData;

/**
 * Created by Aki on 6/22/2017.
 */

public interface MainPresenter {
//    void save(SampleData clickedItem);
    void load();
    void onResume();
}
