package sg.vinova.aki.mvplogin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Aki on 6/20/2017.
 */

public class ListAccountFile {
    File file;
    Context context;

    public ListAccountFile(Context context, File file) {
        this.context = context;
        this.file = file;
    }

    void save(Bitmap bitmap, String name) {
        try {
            File file = new File(context.getFilesDir(), name);
            FileOutputStream outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.close();
            Log.d("File", "Saved Image: " + context.getFilesDir() + "//" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Bitmap load(String name) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(context.getFilesDir() + "//" + name));
            Log.d("File", "Loaded Image: " + context.getFilesDir() + "//" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    void save(String username, String password) {
        try {
            FileOutputStream outputStream = context.openFileOutput(file.getName(), Context.MODE_APPEND);
            outputStream.write(username.getBytes());
            outputStream.write(" ".getBytes());
            outputStream.write(password.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
            Log.d("File", "File Saved Account: " + username + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void load() {
        try {
            FileInputStream inputStream = context.openFileInput(file.getName());
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
                total.append("\n");
            }
            r.close();
            inputStream.close();
            Log.d("File", "File contents: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean delete() {
        return file.delete();
    }
}
