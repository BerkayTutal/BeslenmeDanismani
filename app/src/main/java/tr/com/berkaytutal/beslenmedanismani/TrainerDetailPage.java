package tr.com.berkaytutal.beslenmedanismani;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.CertificatesAdapter;
import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.CertificatePOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.FunctionUtils;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;

public class TrainerDetailPage extends BaseDrawerActivity {

    private ImageView profileImage;
    private TextView profileName;
    private ListView trainerProgramsListView;
    private ListView trainerCertificatesListView;
    private TextView ratingTextView;
    private TextView bioTextView;
    private TextView introTextVİew;
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
        bioTextView = (TextView) findViewById(R.id.trainerProfileBio);
        introTextVİew = (TextView) findViewById(R.id.trainerProfileBio);
        ratingTextView = (TextView) findViewById(R.id.trainerProfileRating);
        trainerCertificatesListView = (ListView) findViewById(R.id.trainerDetailCertificatesListView);


        profileImage.setImageBitmap(user.getPhoto());
        profileName.setText(user.getName() + " " + user.getSurname());

        String bioText = user.getBio();

        try{
            if(!bioText.equals("")){
                bioTextView.setText(bioText);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        String introText = user.getIntro();

        try {
            if (!introText.equals("")) {
                introTextVİew.setText(introText);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO rating

        ArrayList<CertificatePOJO> certificates = user.getCertificates();
        CertificatesAdapter certAdapter = new CertificatesAdapter(this,certificates);
        trainerCertificatesListView.setAdapter(certAdapter);
        FunctionUtils.setListViewHeightBasedOnItems(trainerCertificatesListView);



        ArrayList<ProgramPOJO> trainerPrograms = ((GlobalVariables) getApplicationContext()).getProgramsByTrainerID(userID);


        ProgramListingAdapter adapter = new ProgramListingAdapter(this, trainerPrograms);
        trainerProgramsListView.setAdapter(adapter);

        FunctionUtils.setListViewHeightBasedOnItems(trainerProgramsListView);

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
