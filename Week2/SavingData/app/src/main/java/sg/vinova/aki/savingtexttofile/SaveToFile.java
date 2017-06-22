package sg.vinova.aki.savingtexttofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Aki on 6/21/2017.
 */

public class SaveToFile {

    Context context;
    File file;

    public SaveToFile(Context context, String filename) {
        this.context = context;
        file = new File(context.getFilesDir(), filename);
    }

//    void save(Bitmap bitmap, String name) {
//        try {
//            File file = new File(context.getFilesDir(), name);
//            FileOutputStream outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
//            outputStream.close();
//            Log.d("File", "Saved Image: " + context.getFilesDir() + "//" + name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    Bitmap load(String name) {
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream(new FileInputStream(context.getFilesDir() + "//" + name));
//            Log.d("File", "Loaded Image: " + context.getFilesDir() + "//" + name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }

    void write(String data) {
        try {
            FileOutputStream outputStream = context.openFileOutput(file.getName(), Context.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
            Log.d("File", "Saved Data: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    StringBuilder read() {
        StringBuilder total = new StringBuilder();
        try {
            FileInputStream inputStream = context.openFileInput(file.getName());
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

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
        return total;
    }

    void remove() {
        if (file.delete()) {
            Log.d("File", "File removed");
        }
    }

    SaveToFile fileClone() {
        SaveToFile stf = new SaveToFile(context, file.getName() + "Copy");
        stf.remove();
        stf.write(this.read().toString());
        Log.d("File", "File cloned");
        return stf;
    }

    public File getFile() {
        return file;
    }
}
