package sg.vinova.aki.myapplication;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import layout.Deliveries;
import layout.Explore;
import model.Delivery;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        Deliveries.OnFragmentInteractionListener,
        Explore.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    ArrayList<String> filteredCuisines;
    ArrayList<CheckedTextView> listCheckedTextView;
    List<Delivery> deliveries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
                } else {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
                }
            }

        };

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SeekBar seekBar = (SeekBar) findViewById(R.id.my_seekbar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }

        });

        // Flowlayout ------------------------------------

        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("African");
        cuisines.add("Arabic");
        cuisines.add("American");
        cuisines.add("Burger");
        cuisines.add("Breakfast");
        cuisines.add("Caribbea");
        cuisines.add("Chicken");
        cuisines.add("Danish");

        listCheckedTextView = new ArrayList<>();
        filteredCuisines = new ArrayList<>();
        FlowLayout fl = (FlowLayout) findViewById(R.id.flow_layout);

        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckedTextView) v).isChecked()) {
                    filteredCuisines.remove(((CheckedTextView) v).getText().toString());

                    ((CheckedTextView) v).setChecked(false);
                    ((CheckedTextView) v).setTextColor(getResources().getColor(R.color.btn_default_text_color));
                    v.setBackground(getResources().getDrawable(R.drawable.button_default));
                } else {
                    filteredCuisines.add(((CheckedTextView) v).getText().toString());

                    ((CheckedTextView) v).setChecked(true);
                    ((CheckedTextView) v).setTextColor(getResources().getColor(R.color.btn_activated_text_color));
                    v.setBackground(getResources().getDrawable(R.drawable.button_activated));
                }
            }
        };

        FlowLayout.LayoutParams layoutParams;
        CheckedTextView myCheckedTextView;
        for (String i : cuisines) {
            layoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            myCheckedTextView = new CheckedTextView(this);

            myCheckedTextView.setText(i);
            myCheckedTextView.setTextColor(getResources().getColor(R.color.btn_default_text_color));
            myCheckedTextView.setBackground(getResources().getDrawable(R.drawable.button_default));
            myCheckedTextView.setChecked(false);
            myCheckedTextView.setOnClickListener(myOnClickListener);

            layoutParams.setMargins(5, 5, 5, 5);
            fl.addView(myCheckedTextView, layoutParams);
            listCheckedTextView.add(myCheckedTextView);
        }

        // -----------------
        deliveries = new ArrayList<>();

        deliveries.add(new Delivery("Casual Lunch", "Bravi Ragazzi", "Streatham High rd", "a", "https://thelondonpizzablog.files.wordpress.com/2014/05/braviragazzi_bottom.jpg", "$$$"));
        deliveries.add(new Delivery("Casual Lunch", "Joe's kitchen", "Streatham High rd", "a", "https://thelondonpizzablog.files.wordpress.com/2014/05/braviragazzi_bottom.jpg", "$$"));
        deliveries.add(new Delivery("Winter warmers", "asd", "Streatham High rd", "a", "https://thelondonpizzablog.files.wordpress.com/2014/05/braviragazzi_bottom.jpg", "$$"));
        deliveries.add(new Delivery("Lunch", "Bravi Ragazzi", "Streatham High rd", "a", "https://thelondonpizzablog.files.wordpress.com/2014/05/braviragazzi_bottom.jpg", "$$$"));
        deliveries.add(new Delivery("Lunch", "Joe's kitchen", "Streatham High rd", "a", "https://thelondonpizzablog.files.wordpress.com/2014/05/braviragazzi_bottom.jpg", "$$"));

        navigationView.getMenu().performIdentifierAction(R.id.nav_deliveries, 0);
        setTitle("Deliveries");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filters) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fm = null;
        if (id == R.id.nav_deliveries) {
            fm = Deliveries.newInstance(deliveries);
        } else if (id == R.id.nav_explore) {
            fm = Explore.newInstance(deliveries);
        } else if (id == R.id.nav_orders_history) {

        } else if (id == R.id.nav_contributions) {

        } else if (id == R.id.nav_help_and_feedback) {

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_placeholder, fm)
                .commitAllowingStateLoss();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all:
                for (CheckedTextView i : listCheckedTextView) {
                    if (!i.isChecked()) {
                        i.performClick();
                    }
                }
                break;
            case R.id.btn_none:
                for (CheckedTextView i : listCheckedTextView) {
                    if (i.isChecked()) {
                        i.performClick();
                    }
                }
                break;
        }
    }
}
