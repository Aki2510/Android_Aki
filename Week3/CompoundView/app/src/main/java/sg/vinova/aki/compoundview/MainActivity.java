package sg.vinova.aki.compoundview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompoundView compoundView = (CompoundView) findViewById(R.id.compound_view);

        compoundView.setData( new CharSequence[] {
                "1","22","333","4444","5","6"
        });

    }
}
