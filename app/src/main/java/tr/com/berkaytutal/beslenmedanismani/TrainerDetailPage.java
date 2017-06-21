package tr.com.berkaytutal.beslenmedanismani;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;

public class TrainerDetailPage extends BaseDrawerActivity {

    private ImageView profileImage;
    private TextView profileName;
    private ListView trainerProgramsListView;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_detail_page);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        userID = getIntent().getIntExtra("userID", -1);


        TrainerPOJO user = ((GlobalVariables) getApplicationContext()).getUserByID(userID);

        profileImage = (ImageView) findViewById(R.id.trainerProfileImage);
        profileName = (TextView) findViewById(R.id.trainerProfileName);
        trainerProgramsListView = (ListView) findViewById(R.id.trainerDetailProgramsListView);

        profileImage.setImageBitmap(user.getPhoto());
        profileName.setText(user.getName() + " " + user.getSurname());


        ArrayList<ProgramPOJO> trainerPrograms = ((GlobalVariables) getApplicationContext()).getProgramsByTrainerID(userID);


        ProgramListingAdapter adapter = new ProgramListingAdapter(this, trainerPrograms);
        trainerProgramsListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


}
