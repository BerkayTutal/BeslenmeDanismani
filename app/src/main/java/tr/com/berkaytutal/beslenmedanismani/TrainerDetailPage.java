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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_detail_page);
        int userID = getIntent().getIntExtra("userID",-1);
        TrainerPOJO user = ((GlobalVariables)getApplicationContext()).getUserByID(userID);

        profileImage = (ImageView) findViewById(R.id.trainerProfileImage);
        profileName = (TextView) findViewById(R.id.trainerProfileName);
        trainerProgramsListView = (ListView) findViewById(R.id.trainerDetailProgramsListView);

        profileImage.setImageBitmap(user.getPhoto());
        profileName.setText(user.getName() + " " + user.getSurname());



        ArrayList<ProgramPOJO> trainerPrograms = ((GlobalVariables)getApplicationContext()).getProgramsByTrainerID(userID);
        trainerPrograms.addAll(trainerPrograms);
        trainerPrograms.addAll(trainerPrograms);
        trainerPrograms.addAll(trainerPrograms);

        ProgramListingAdapter adapter = new ProgramListingAdapter(this,trainerPrograms);
        trainerProgramsListView.setAdapter(adapter);




    }
}
