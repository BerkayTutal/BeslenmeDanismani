package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    private TrainerPOJO user;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_detail_page);


        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        userID = getIntent().getIntExtra("userID", -1);

        this.activity = this;
        try {
            user = ((GlobalVariables) getApplicationContext()).getUserByID(userID);
            setTheRest();
        } catch (Exception e) {
            e.printStackTrace();
            TrainerCheckAsync asy = new TrainerCheckAsync();
            asy.execute("test");

        }


    }

    private class TrainerCheckAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            if (FunctionUtils.isInternetAvailable()) {
                //TODO download trainer details
                return "OK";
            } else {

                return "nointernet";

            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if ("OK".equals(o.toString())) {
                setTheRest();
            } else {
                //TODO internet yok canım progressDialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        activity);

                // set title
                // alertDialogBuilder.setTitle("Info");

                // set progressDialog message
                alertDialogBuilder
                        .setMessage(R.string.needInternet)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                activity.finish();
                                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                            }
                        });


                // create alert progressDialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
            super.onPostExecute(o);

        }
    }

    private void setTheRest() {
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

        try {
            if (!bioText.equals("")) {
                bioTextView.setText(bioText);
            }

        } catch (Exception e) {
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
        CertificatesAdapter certAdapter = new CertificatesAdapter(this, certificates);
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
