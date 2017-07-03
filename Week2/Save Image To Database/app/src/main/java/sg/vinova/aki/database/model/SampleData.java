package sg.vinova.aki.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aki on 6/22/2017.
 */

@DatabaseTable(tableName = "sampledata")
public class SampleData {
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String image;

    @DatabaseField(canBeNull = false)
    String imageName;

    @DatabaseField
    String imagePath;

    @DatabaseField
    String description;

    @DatabaseField
    String description2;

    public SampleData() {
    }

    public SampleData(String image) {
        this.image = image;
        this.imageName = "Default";
    }

    public SampleData(String image, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }

    public SampleData(String image, String imageName, String imagePath) {
        this.image = image;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "SampleData{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
