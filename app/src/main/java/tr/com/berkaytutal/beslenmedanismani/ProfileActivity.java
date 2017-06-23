package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.DBHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.DataSenderHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class ProfileActivity extends BaseDrawerActivity {

    private ProgramListingAdapter adapter;

    private ImageView profileImage;
    private TextView profileName;
    private TextView profileEmail;
    private TextView profileBirthday;
    private ImageView profileGender;
    private UserDataPOJO userDataPOJO;
    private ListView myProgramsListview;
    private ArrayList<ProgramPOJO> myProgramsArrayList;
    private Button editDetails;


    private String tall;
    private String weight;
    private String muscleRate;
    private String fatRate;
    private String waterRate;


    private EditText tallEditText;
    private EditText weightEditText;
    private EditText muscleRateEditText;
    private EditText fatRateEditText;
    private EditText waterRateEditText;
    private Button updateButton;

    private Button seeAllMyProgramsButton;


    private JSONObject jsonObject;


    public static Activity profileActivity;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutButton = menu.findItem(R.id.appBarLogoutButton);
        logoutButton.setVisible(true);

        MenuItem profileButton = menu.findItem(R.id.appBarProfileButton);
        profileButton.setVisible(false);


        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


    }

    @Override
    protected void onResume() {
        super.onResume();


        profileActivity = this;

        userDataPOJO = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();

        myProgramsListview = (ListView) findViewById(R.id.myProgramsListviewDetailPage);

        profileImage = (ImageView) findViewById(R.id.userProfileImageDetailPage);
        profileName = (TextView) findViewById(R.id.userProfileNameDetailPage);
        profileEmail = (TextView) findViewById(R.id.userMailDetailPage);
        profileBirthday = (TextView) findViewById(R.id.userBirthdayDetailPage);
        profileGender = (ImageView) findViewById(R.id.userDetailGenderIcon);
        editDetails = (Button) findViewById(R.id.userProfileEditDetails);
        seeAllMyProgramsButton = (Button) findViewById(R.id.seeAllMyProgramsButton);

        seeAllMyProgramsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MyProgramsActivity.class);
                startActivity(i);
            }
        });

        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(i);

            }
        });

//        profileImage.setImageBitmap(userDataPOJO.ge);
        profileName.setText(userDataPOJO.getName() + " " + userDataPOJO.getSurname());
        profileEmail.setText(userDataPOJO.getEmail());
        profileBirthday.setText(userDataPOJO.getBirthday());

        if (userDataPOJO.getSex().equals("M")) {
            profileGender.setImageDrawable(getResources().getDrawable(R.drawable.male));
        } else {
            profileGender.setImageDrawable(getResources().getDrawable(R.drawable.female));
        }


        myProgramsArrayList = userDataPOJO.getMyPrograms();
        adapter = new ProgramListingAdapter(this, myProgramsArrayList);
        myProgramsListview.setAdapter(adapter);
//        myProgramsListview.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.homeListingHeight));


//        tall = userDataPOJO.getTall();
//        weight = userDataPOJO.getWeight();
//        muscleRate = userDataPOJO.getMuscleRate();
//        fatRate = userDataPOJO.getFatRate();
//        waterRate = userDataPOJO.getWaterRate();

//        tallEditText = (EditText) findViewById(R.id.bodyRatesTallEditText);
//        weightEditText = (EditText) findViewById(R.id.bodyRatesWeightEditText);
//        muscleRateEditText = (EditText) findViewById(R.id.bodyRatesMuscleRateEditText);
//        fatRateEditText = (EditText) findViewById(R.id.bodyRatesFatRateEditText);
//        waterRateEditText = (EditText) findViewById(R.id.bodyRatesWaterRaEditText);
//        updateButton = (Button) findViewById(R.id.bodyRatesUpdateButton);
//
//        tallEditText.setText(tall);
//        weightEditText.setText(weight);
//        muscleRateEditText.setText(muscleRate);
//        fatRateEditText.setText(fatRate);
//        waterRateEditText.setText(waterRate);
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tall = tallEditText.getText().toString();
//                weight = weightEditText.getText().toString();
//                muscleRate = muscleRateEditText.getText().toString();
//                fatRate = fatRateEditText.getText().toString();
//                waterRate = waterRateEditText.getText().toString();
//                jsonObject = new JSONObject();
//                try {
//                    jsonObject.accumulate("fatRate", fatRate);
//                    jsonObject.accumulate("muscleRate", muscleRate);
//                    jsonObject.accumulate("tall", tall);
//                    jsonObject.accumulate("user_ID", userDataPOJO.getUser_ID());
//                    jsonObject.accumulate("waterRate", waterRate);
//                    jsonObject.accumulate("weight", weight);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                MyAsyncClass2 async = new MyAsyncClass2();
//                async.execute("test");
//
//
//            }
//        });

//TODO linechart kısmı burada
        LineChart lineChart = (LineChart) findViewById(R.id.bodyRatesChartProfile);

        List<Entry> bodyRateEntries = new ArrayList<Entry>();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


    private class MyAsyncClass2 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            Log.i("body", "update isteği yollandı");
//            Toast.makeText(getApplicationContext(), "istek Gönderildi", Toast.LENGTH_SHORT).show();
            return DataSenderHelper.POST(PublicVariables.bodyRateUpdateURL, jsonObject);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("false")) {
                Log.e("body", "sorun oluştu");
                Toast.makeText(getApplicationContext(), "sorun oluştu", Toast.LENGTH_SHORT).show();
            } else if (result.equals("true")) {
                Log.i("body", "update başarılı");
//                userDataPOJO.setFatRate(fatRate);
//                userDataPOJO.setMuscleRate(muscleRate);
//                userDataPOJO.setTall(tall);
//                userDataPOJO.setWaterRate(waterRate);
//                userDataPOJO.setWeight(weight);
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.updateUser(userDataPOJO);
                ((GlobalVariables) getApplicationContext()).setUserDataPOJO(userDataPOJO);
                Toast.makeText(getApplicationContext(), "Başarılı", Toast.LENGTH_SHORT).show();


            }

        }
    }
}
