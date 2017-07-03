package sg.vinova.aki.webservicejsonretrofit.view;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import sg.vinova.aki.webservicejsonretrofit.R;
import sg.vinova.aki.webservicejsonretrofit.model.Service;
import sg.vinova.aki.webservicejsonretrofit.model.entity.City;
import sg.vinova.aki.webservicejsonretrofit.view.adapter.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements CityWeatherFragment.OnFragmentInteractionListener,
        Service.OnGetCityListener {
    Toolbar myToolbar;
    ViewPager viewPager;
    MyViewPagerAdapter adapter;

    Dialog dialog;
    String[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        viewPager = (ViewPager) findViewById(R.id.fragment_placeholder);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), this);
        adapter.add(getString(R.string.accuweather_hcmc_id));
        viewPager.setAdapter(adapter);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment_placeholder, CityWeatherFragment.newInstance(getString(R.string.accuweather_hcmc_id)))
//                .commit();

        String citiesStr = "An Giang\n" +
                "Bà Rịa - Vũng Tàu\n" +
                "Bắc Giang\n" +
                "Bắc Kạn\n" +
                "Bạc Liêu\n" +
                "Bắc Ninh\n" +
                "Bến Tre\n" +
                "Bình Định\n" +
                "Bình Dương\n" +
                "Bình Phước\n" +
                "Bình Thuận\n" +
                "Cà Mau\n" +
                "Cao Bằng\n" +
                "Đắk Lắk\n" +
                "Đắk Nông\n" +
                "Điện Biên\n" +
                "Đồng Nai\n" +
                "Đồng Tháp\n" +
                "Gia Lai\n" +
                "Hà Giang\tHà Nam\n" +
                "Hà Tĩnh\n" +
                "Hải Dương\n" +
                "Hậu Giang\n" +
                "Hòa Bình\n" +
                "Hưng Yên\n" +
                "Khánh Hòa\n" +
                "Kiên Giang\n" +
                "Kon Tum\n" +
                "Lai Châu\n" +
                "Lâm Đồng\n" +
                "Lạng Sơn\n" +
                "Lào Cai\n" +
                "Long An\n" +
                "Nam Định\n" +
                "Nghệ An\n" +
                "Ninh Bình\n" +
                "Ninh Thuận\n" +
                "Phú Thọ\n" +
                "Quảng Bình\tQuảng Nam\n" +
                "Quảng Ngãi\n" +
                "Quảng Ninh\n" +
                "Quảng Trị\n" +
                "Sóc Trăng\n" +
                "Sơn La\n" +
                "Tây Ninh\n" +
                "Thái Bình\n" +
                "Thái Nguyên\n" +
                "Thanh Hóa\n" +
                "Thừa Thiên Huế\n" +
                "Tiền Giang\n" +
                "Trà Vinh\n" +
                "Tuyên Quang\n" +
                "Vĩnh Long\n" +
                "Vĩnh Phúc\n" +
                "Yên Bái\n" +
                "Phú Yên\tCần Thơ\n" +
                "Đà Nẵng\n" +
                "Hải Phòng\n" +
                "Hà Nội\n" +
                "TP HCM";

        cities = citiesStr.split("\n");

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
//                adapter.add(getString(R.string.accuweather_ha_noi_id));
                showDialog();
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("Chọn tỉnh thành: ");
        View v = LayoutInflater.from(this).inflate(R.layout.listview_cities, null, false);
        dialog.setContentView(v);

        ListView listView = (ListView) dialog.findViewById(R.id.list_cities);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView) view.findViewById(android.R.id.text1)).setTextColor(position % 2 == 0 ? Color.BLACK : Color.BLUE);
                return view;
            }
        });

        final Service service = Service.newInstance();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                service.getCities(cities[position], "bXlKlJaOrvVFbSxsIYEh8wa7eGBGpqnH", MainActivity.this);
            }
        });
        dialog.show();
    }

    @Override
    public void onSuccessful(City city) {
        adapter.add(city.getKey());
        viewPager.setCurrentItem(adapter.getCount() - 1);
        dialog.hide();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, "failed eee", Toast.LENGTH_SHORT).show();
    }
}
