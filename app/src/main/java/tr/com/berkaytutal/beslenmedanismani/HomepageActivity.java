package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
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


        ProgramListingAdapter pla = new ProgramListingAdapter(this, nPrograms);
        programsListView.setAdapter(pla);

        UserListingAdapter ula = new UserListingAdapter(this, nUsers);

        trainersListView.setAdapter(ula);


    }


}
