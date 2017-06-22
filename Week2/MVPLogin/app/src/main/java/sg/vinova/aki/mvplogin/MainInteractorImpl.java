package sg.vinova.aki.mvplogin;

import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by Aki on 6/20/2017.
 */

public class MainInteractorImpl implements MainInteractor {

    @Override
    public void login(final String username, final String password, final onFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    error = true;
                } else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    error = true;
                }

                if (!error) {
                    listener.onSuccess(username, password);
                }
            }
        }, 2000);
    }
}

