package sg.vinova.aki.webservicejsonretrofit;

import android.content.Context;
import android.graphics.drawable.Drawable;

import sg.vinova.aki.webservicejsonretrofit.R;

/**
 * Created by Aki on 7/2/2017.
 */

public class IconGetter {
    Context context;

    public IconGetter(Context context) {
        this.context = context;
    }

    public Drawable getIcon(int id) {
        int resourceId = -1;
        switch (id) {
            case 1:
                resourceId = R.drawable._1;
                break;
            case 2:
                resourceId = R.drawable._2;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                resourceId = R.drawable._6;
                break;
            case 7:
                resourceId = R.drawable._7;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                resourceId = R.drawable._11;
                break;
            case 12:
                resourceId = R.drawable._12;
                break;
            case 13:
                resourceId = R.drawable._13;
                break;
            case 14:
            case 15:
                resourceId = R.drawable._15;
                break;
            case 16:
                resourceId = R.drawable._16;
                break;
            case 17:
            case 18:
                resourceId = R.drawable._18;
                break;
            case 19:
                resourceId = R.drawable._19;
                break;
            case 20:
                resourceId = R.drawable._20;
                break;
            case 21:
            case 22:
                resourceId = R.drawable._22;
                break;
            case 23:
                resourceId = R.drawable._23;
                break;
            case 24:
                resourceId = R.drawable._24;
                break;
            default:
                resourceId = R.mipmap.ic_launcher;
        }
        return context.getResources().getDrawable(resourceId);
    }
}
