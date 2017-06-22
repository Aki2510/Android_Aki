package sg.vinova.aki.mvplogin;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {
    EditText etUsername;
    EditText etPassword;
    Button btnButton;
    MainPresenterImpl mainPresenter;

    File file;
    final String filename = "abc";
    ListAccountFile listAccountFile;

    ImageView image;
    ImageView image2;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btnButton = (Button) findViewById(R.id.button);
        btnButton.setOnClickListener(this);

        file = new File(getFilesDir(), filename);
        listAccountFile = new ListAccountFile(this, file);

        mainPresenter = new MainPresenterImpl(this, listAccountFile);

        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);

        if (savedInstanceState == null) {
            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg")
                    .into(new SimpleTarget<Bitmap>(100, 100) {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            bitmap = resource;
                            image.setImageBitmap(bitmap);
                            mainPresenter.save(bitmap, "1");
                        }
                    });
        }
    }


    @Override
    public void showUsernameError() {
        etUsername.setError("Error");
    }

    @Override
    public void showPasswordError() {
        etPassword.setError("Error");
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mainPresenter.check(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.button2:
                mainPresenter.load();
                break;
            case R.id.button3:
                mainPresenter.delete();
                break;
            case R.id.image:
                Bitmap bitmap = mainPresenter.load("1");
                if (bitmap == null) {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                } else {
                    image2.setImageBitmap(bitmap);
                }
                break;
        }
    }
}
