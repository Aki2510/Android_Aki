package sg.vinova.aki.database.model.database;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Created by Aki on 6/22/2017.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}