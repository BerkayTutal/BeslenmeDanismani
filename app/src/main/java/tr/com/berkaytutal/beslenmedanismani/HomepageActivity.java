package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Adapters.UserListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;

public class HomepageActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Intent i = new Intent(this,AllUsersActivity.class);
//        startActivity(i);


        ListView programList = (ListView) findViewById(R.id.homeFirstList);
        ListView programList2 = (ListView) findViewById(R.id.homeFirstList2);

        ArrayList<Integer> arr = new ArrayList<>(23);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);


        ProgramListingAdapter pla = new ProgramListingAdapter(this,arr, PublicVariables.TYPE_LISTINGS_HOMEPAGE);
        programList.setAdapter(pla);

        UserListingAdapter ula = new UserListingAdapter(this,arr,PublicVariables.TYPE_LISTINGS_HOMEPAGE);

        programList2.setAdapter(ula);





    }
}
