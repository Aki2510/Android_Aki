package sg.vinova.aki.mvplogin;

/**
 * Created by Aki on 6/20/2017.
 */

public interface MainInteractor {
    interface onFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess(String username, String password);
    }
    void login(String username, String password, onFinishedListener listener);
}
