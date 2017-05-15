package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        Intent i = new Intent(this,HomepageActivity.class);
        startActivity(i);
        finish();
    }
}
