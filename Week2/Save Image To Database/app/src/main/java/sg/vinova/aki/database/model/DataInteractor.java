package sg.vinova.aki.database.model;

import android.os.AsyncTask;
import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aki on 6/22/2017.
 */

public class DataInteractor {
    private List<SampleData> generateSampleList() {
        List<SampleData> listSampleImage = new ArrayList<>();
        listSampleImage.add(new SampleData("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg", "Meo"));
        listSampleImage.add(new SampleData("https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg", "Cu"));
        listSampleImage.add(new SampleData("https://amazingslider.com/wp-content/uploads/2012/12/dandelion.jpg", "Bo cong anh"));
        listSampleImage.add(new SampleData("http://i.dailymail.co.uk/i/pix/2016/04/13/00/331D901800000578-3536787-image-a-11_1460503122350.jpg", "Mat"));
        listSampleImage.add(new SampleData("https://www.nasa.gov/images/content/174735main_LEFTFullDisk.jpg", "Tron tron Xanh xanh"));
        listSampleImage.add(new SampleData("https://www.noao.edu/image_gallery/images/d3/04535.jpg", "1"));
        listSampleImage.add(new SampleData("http://images.ipac.caltech.edu/spitzer/sig11-003/spitzer_sig11-003_6000.jpg", "2"));

        listSampleImage.add(new SampleData("http://images.ipac.caltech.edu/spitzer/sig11-003/spitzer_sig11-003_6000.jpg", "2"));
        return listSampleImage;
    }

    public void getList(onGetListFinishedListener listener){
        listener.onGetListFinished(generateSampleList());
    }

    public interface onGetListFinishedListener {
        void onGetListFinished(List<SampleData> list);
    }
}
