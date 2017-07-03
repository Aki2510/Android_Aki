package sg.vinova.aki.webservicejsonretrofit.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sg.vinova.aki.webservicejsonretrofit.R;
import sg.vinova.aki.webservicejsonretrofit.view.CityWeatherFragment;

/**
 * Created by Aki on 7/3/2017.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = null;
    private Context context;

    public MyViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
//        switch (position) {
//            case 0:
//                return CityWeatherFragment.newInstance(context.getResources().getString(R.string.accuweather_hcmc_id));
//            case 1:
//                return CityWeatherFragment.newInstance(context.getResources().getString(R.string.accuweather_ha_noi_id));
//            default:
//                return list.get(position);
//        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    public void add(String locationId){
        list.add(CityWeatherFragment.newInstance(locationId));
        notifyDataSetChanged();

    }
}