package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class MyProgramsActivity extends BaseDrawerActivity {

    private ListView myProgramsListview;
    private ArrayList<ProgramPOJO> myProgramsArrayList;
    private UserDataPOJO userDataPOJO;
    private ProgramListingAdapter adapter;



    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutButton = menu.findItem(R.id.appBarLogoutButton);
        logoutButton.setVisible(true);




        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        userDataPOJO = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();
        myProgramsArrayList = userDataPOJO.getMyPrograms();


        myProgramsListview = (ListView) findViewById(R.id.myProgramsListViewActivity);

        adapter = new ProgramListingAdapter(this,myProgramsArrayList);

        myProgramsListview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);



    }


}
