package sg.vinova.aki.mvplogin;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Aki on 6/20/2017.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.onFinishedListener {

    MainView mainView;
    MainInteractor mainInteractor;

    ListAccountFile listAccountFile;

    public MainPresenterImpl(MainView mainView, ListAccountFile listAccountFile) {
        this.mainView = mainView;
        mainInteractor = new MainInteractorImpl();
        this.listAccountFile = listAccountFile;
    }

    @Override
    public void check(String username, String password) {
        mainInteractor.login(username, password, this);
    }

    public void load() {
        listAccountFile.load();
    }

    public void delete() {
        if (listAccountFile.delete()) {
            Log.d("File", "File deleted successful");
        }
    }

    @Override
    public void onUsernameError() {
        mainView.showUsernameError();
    }

    @Override
    public void onPasswordError() {
        mainView.showPasswordError();
    }

    @Override
    public void onSuccess(String username, String password) {
        mainView.showSuccessMessage();
        listAccountFile.save(username, password);
    }

    public void save(Bitmap bitmap, String name) {
        listAccountFile.save(bitmap, name);
    }

    public Bitmap load(String name) {
        return listAccountFile.load(name);
    }
}
