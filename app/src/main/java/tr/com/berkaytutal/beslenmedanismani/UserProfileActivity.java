package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;

public class UserProfileActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        searchView.setVisibility(View.GONE);
    }
}
