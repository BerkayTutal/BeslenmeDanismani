package tr.com.berkaytutal.beslenmedanismani;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class ProfileActivity extends BaseDrawerActivity {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profileEmail;
    private TextView profileBirthday;
    private ImageView profileGender;
    private UserDataPOJO userDataPOJO;
    private ListView myProgramsListview;
    private ArrayList<ProgramPOJO> myProgramsArrayList;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutButton = menu.findItem(R.id.appBarLogoutButton);
        logoutButton.setVisible(true);


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userDataPOJO = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();

        myProgramsListview = (ListView) findViewById(R.id.myProgramsListviewDetailPage);

        profileImage = (ImageView) findViewById(R.id.userProfileImageDetailPage);
        profileName = (TextView) findViewById(R.id.userProfileNameDetailPage);
        profileEmail = (TextView) findViewById(R.id.userMailDetailPage);
        profileBirthday = (TextView) findViewById(R.id.userBirthdayDetailPage);
        profileGender = (ImageView) findViewById(R.id.userDetailGenderIcon);

//        profileImage.setImageBitmap(userDataPOJO.ge);
        profileName.setText(userDataPOJO.getName() + " " + userDataPOJO.getSurname());
        profileEmail.setText(userDataPOJO.getEmail());
        profileBirthday.setText(userDataPOJO.getBirthday());

        if (userDataPOJO.getSex().equals("M")) {
            profileGender.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.male));
        } else {
            profileGender.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.female));
        }



        myProgramsArrayList = userDataPOJO.getMyPrograms();
        ProgramListingAdapter adapter = new ProgramListingAdapter(this, myProgramsArrayList);
        myProgramsListview.setAdapter(adapter);
//        myProgramsListview.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.homeListingHeight));


    }
}
