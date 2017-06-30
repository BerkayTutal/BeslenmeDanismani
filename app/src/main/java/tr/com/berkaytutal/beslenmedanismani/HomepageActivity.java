package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Adapters.UserListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;

public class HomepageActivity extends BaseDrawerActivity {

    private Button seeAllListings;
    private Button seeAllTrainers;
    private TextView textViewUserName;
    private boolean doubleBackToExitPressedOnce = false;
    private ProgramListingAdapter pla;
    private UserListingAdapter ula;



    @Override
    protected void onResume() {
        super.onResume();
        pla.notifyDataSetChanged();
        ula.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.icon_home).setVisible(false);

//
//        Intent rates = new Intent(this,BodyRates.class);
//        startActivity(rates);








        ArrayList<TrainerPOJO> allUsers = ((GlobalVariables) getApplicationContext()).getAllUsers();
        ArrayList<ProgramPOJO> allPrograms = ((GlobalVariables) getApplicationContext()).getAllPrograms();

        ArrayList<TrainerPOJO> nUsers = new ArrayList<>();
        ArrayList<ProgramPOJO> nPrograms = new ArrayList<>();

        for (int i = 0; i < PublicVariables.homepageListingCount && (i < allUsers.size()); i++) {
            nUsers.add(allUsers.get(i));

        }
        for (int i = 0; i < PublicVariables.homepageListingCount && (i < allPrograms.size()); i++) {

            nPrograms.add(allPrograms.get(i));
        }

        seeAllListings = (Button) findViewById(R.id.homepageSeeAllListingsButton);
        seeAllTrainers = (Button) findViewById(R.id.homepageSeeAllTrainersButton);

        seeAllTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AllUsersActivity.class);
                startActivity(i);

            }
        });
        seeAllListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AllListingsActivity.class);
                startActivity(i);

            }
        });


//


//        Intent i = new Intent(this,AllUsersActivity.class);
//        startActivity(i);


        ListView programsListView = (ListView) findViewById(R.id.homeProgramsList);
        ListView trainersListView = (ListView) findViewById(R.id.homeTrainersList);


         pla = new ProgramListingAdapter(this, nPrograms);
        programsListView.setAdapter(pla);

         ula = new UserListingAdapter(this, nUsers);

        trainersListView.setAdapter(ula);


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }
}
