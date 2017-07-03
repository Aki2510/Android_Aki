package sg.vinova.aki.webservicejsonretrofit.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aki on 7/3/2017.
 */

public class City {
    @SerializedName("Key")
    @Expose
    private String key;

    @SerializedName("LocalizedName")
    @Expose
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
