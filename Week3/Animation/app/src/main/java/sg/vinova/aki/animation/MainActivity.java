package sg.vinova.aki.animation;

import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionSet;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_placeholder, RecyclerViewFragment.newInstance())
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
