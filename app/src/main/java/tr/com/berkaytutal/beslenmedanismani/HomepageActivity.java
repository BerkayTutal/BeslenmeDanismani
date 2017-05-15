package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class HomepageActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ListView programList = (ListView) findViewById(R.id.homeFirstList);
        ListView programList2 = (ListView) findViewById(R.id.homeFirstList2);

        ArrayList<Integer> arr = new ArrayList<>(23);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);


        ProgramListingAdapter pla = new ProgramListingAdapter(this,arr);
        programList.setAdapter(pla);

        programList2.setAdapter(pla);




    }
}
