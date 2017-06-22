package sg.vinova.aki.week1_ui2;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivArticleImage = (ImageView) findViewById(R.id.article_image);
        Glide.with(this).load("https://assets3.thrillist.com/v1/image/1930064/size/tmg-slideshow_l.jpg").into(ivArticleImage);

        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Hamburger icon --------------------
        DrawerLayout dl = new DrawerLayout(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
        startActivity(intent);
    }
}
