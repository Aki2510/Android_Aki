package sg.vinova.aki.week1_ui2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import sg.vinova.aki.week1_ui2.adapter.MyAdapter;
import sg.vinova.aki.week1_ui2.model.Model;

public class ScrollingActivity extends AppCompatActivity {
    RecyclerView rvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView ivToolbarImage = (ImageView) findViewById(R.id.toolbar_image);
        Glide.with(this).load("https://assets3.thrillist.com/v1/image/1930064/size/tmg-slideshow_l.jpg").into(ivToolbarImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rvData = (RecyclerView) findViewById(R.id.content_recycler_view);
        rvData.setFocusable(false);
        rvData.setNestedScrollingEnabled(false);

        List<Model> list = new ArrayList<>();
        list.add(new Model("https://sc-news.s3.amazonaws.com/21980/MiamiImage.jpg", "Title", "Author", "Sample Text."));
        list.add(new Model("https://sc-news.s3.amazonaws.com/21980/MiamiImage.jpg", "Title", "Author", "Sample Text."));
        list.add(new Model("https://sc-news.s3.amazonaws.com/21980/MiamiImage.jpg", "Title", "Author", "Sample Text."));
        list.add(new Model("https://sc-news.s3.amazonaws.com/21980/MiamiImage.jpg", "Title", "Author", "Sample Text."));

        MyAdapter adapter = new MyAdapter(this, list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rvData.setLayoutManager(lm);
        rvData.setAdapter(adapter);
    }
}
