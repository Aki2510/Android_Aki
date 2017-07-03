package sg.vinova.aki.animation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aki on 6/27/2017.
 */

public class SampleData implements Serializable {
    String image;
    String title;

    public SampleData(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<SampleData> getSampleData(){
        List<SampleData> list = new ArrayList<>();

        list.add(new SampleData("http://www.jqueryscript.net/images/jQuery-Plugin-For-Fullscreen-Image-Viewer-Chroma-Gallery.jpg", "1"));
        list.add(new SampleData("https://s-media-cache-ak0.pinimg.com/originals/c5/7e/a0/c57ea04f0b2cbdeab1d94a1d0352dfbc.jpg", "2"));
        list.add(new SampleData("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg", "3"));
        list.add(new SampleData("http://www.jqueryscript.net/images/jQuery-Plugin-For-Fullscreen-Image-Viewer-Chroma-Gallery.jpg", "1"));
        list.add(new SampleData("https://s-media-cache-ak0.pinimg.com/originals/c5/7e/a0/c57ea04f0b2cbdeab1d94a1d0352dfbc.jpg", "2"));
        list.add(new SampleData("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg", "3"));
        list.add(new SampleData("http://www.jqueryscript.net/images/jQuery-Plugin-For-Fullscreen-Image-Viewer-Chroma-Gallery.jpg", "1"));
        list.add(new SampleData("https://s-media-cache-ak0.pinimg.com/originals/c5/7e/a0/c57ea04f0b2cbdeab1d94a1d0352dfbc.jpg", "2"));
        list.add(new SampleData("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg", "3"));

        return list;
    }
}
