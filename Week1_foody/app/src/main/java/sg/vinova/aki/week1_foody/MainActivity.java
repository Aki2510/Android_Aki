package sg.vinova.aki.week1_foody;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import layout.MagazineFragment;
import layout.ProfileFragment;
import layout.SelectFragment;

public class MainActivity extends AppCompatActivity implements MagazineFragment.OnFragmentInteractionListener, SelectFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
    ImageView ivToolbarImage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fm = null;
            switch (item.getItemId()) {
                case R.id.navigation_select:
                    fm = SelectFragment.newInstance();
                    getSupportActionBar().setTitle("Foodi");
                    ivToolbarImage.setVisibility(View.GONE);
                    break;
                case R.id.navigation_magazine:
                    fm = MagazineFragment.newInstance();
                    getSupportActionBar().setTitle("Foodi Mag");
                    ivToolbarImage.setVisibility(View.GONE);
                    break;
                case R.id.navigation_profile:
                    fm = ProfileFragment.newInstance();
                    getSupportActionBar().setTitle("");
                    ivToolbarImage.setVisibility(View.VISIBLE);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fm).commitAllowingStateLoss();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ivToolbarImage = (ImageView) findViewById(R.id.my_toolbar_image);
        Glide.with(this).load("https://platform.howcloud.com/img/UI/avatar-default-user.png").into(ivToolbarImage);

        navigation.setSelectedItemId(R.id.navigation_select);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
