package sg.vinova.aki.savingtexttofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String filename = "data";
    EditText editText;
    SaveToFile saveToFile;
    SaveToFile clonedFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);

        saveToFile = new SaveToFile(this, filename);
        Toast.makeText(this, getFilesDir().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                saveToFile.write(editText.getText().toString());
                break;
            case R.id.read:
                saveToFile.read();
                break;
            case R.id.remove:
                saveToFile.remove();
                break;
            case R.id.clone:
                clonedFile = saveToFile.fileClone();
                break;
            case R.id.read_clone:
                if (clonedFile != null) {
                    clonedFile.read();
                    break;
                }
        }
    }
}
