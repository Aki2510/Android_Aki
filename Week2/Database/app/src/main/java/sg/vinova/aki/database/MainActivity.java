package sg.vinova.aki.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtAge;
    Button btnAdd;
    Button btnDelete;
    Button btnShowAll;
    Button btnUpdate;
    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;
    Dao<Person, Integer> personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtAge = (EditText) findViewById(R.id.edt_age);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnShowAll = (Button) findViewById(R.id.btn_show_all);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                try {
                    personDao = getHelper().getPersonDao();
                    personDao.create(new Person(edtName.getText().toString(), Integer.parseInt(edtAge.getText().toString())));
                    Toast.makeText(this, "added: " + edtName.getText().toString(), Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_delete:
                try {
                    personDao = getHelper().getPersonDao();
                    DeleteBuilder<Person, Integer> db = personDao.deleteBuilder();
                    db.where().eq("name", edtName.getText().toString());
                    personDao.delete(db.prepare());
                    Toast.makeText(this, "deleted: " + edtName.getText().toString(), Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_show_all:
                try {
                    personDao = getHelper().getPersonDao();
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, personDao.queryForAll());
                    recyclerView.setAdapter(adapter);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_update:

                break;
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
