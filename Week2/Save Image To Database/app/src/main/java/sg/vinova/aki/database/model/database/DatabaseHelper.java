package sg.vinova.aki.database.model.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import sg.vinova.aki.database.model.SampleData;

/**
 * Created by Aki on 6/22/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 3;

    private Dao<SampleData, Integer> sampleDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, SampleData.class);

            Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: ");
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
//        try {
//            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
//            TableUtils.dropTable(connectionSource, SampleData.class, true);
//
//            // after we drop the old databases, we create the new ones
//            onCreate(sqLiteDatabase, connectionSource);
        if (i < 2) {
            try {
                Dao<SampleData, Integer> dao = getSimpleDataDao();
                dao.executeRaw("ALTER TABLE `sampledata` ADD COLUMN description STRING;");
                Log.d("Database", "Added column description");
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Error ", e);
            }
        }
        if (i<3) {
            try {
                Dao<SampleData, Integer> dao = getSimpleDataDao();
                dao.executeRaw("ALTER TABLE `sampledata` ADD COLUMN description2 STRING;");
                Log.d("Database", "Added column description");
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Error ", e);
            }
        }

//
//        } catch (SQLException e) {
//            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
//            throw new RuntimeException(e);
//        }
    }

    public Dao<SampleData, Integer> getSimpleDataDao() throws SQLException {
        if (sampleDao == null) {
            sampleDao = getDao(SampleData.class);
        }
        return sampleDao;
    }

    @Override
    public void close() {
        super.close();
        sampleDao = null;
    }
}