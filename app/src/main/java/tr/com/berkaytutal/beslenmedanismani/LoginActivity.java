package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private boolean isMainLogin;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button loginButton;
    private TextView continueTextView;

    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);


        emailEditText = (EditText) findViewById(R.id.emailAddressLoginScreen);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginScreen);
        registerButton = (Button) findViewById(R.id.registerButtonLoginScreen);
        loginButton = (Button) findViewById(R.id.loginButtonLoginScreen);
        continueTextView = (TextView) findViewById(R.id.continueWithoutLoginTextView);


        isMainLogin = getIntent().getBooleanExtra("isMainLogin", false);
        //TODO burada da herhangi bir yerden erişmiş olacak, eğer login olduysa profil sayfasına yönlenecek, olmadysa login sayfası gelecek ve login olunca profil sayfasına gidecek


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                Toast.makeText(getApplicationContext(), email + " " + password + " login", Toast.LENGTH_SHORT).show();

                if (isMainLogin) {
                    Toast.makeText(getApplicationContext(), "main login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO burası registeractivity'ye gidecek direkt, varsa mail ve password da yolla

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                Intent i = new Intent(view.getContext(),RegisterActivity.class);
                i.putExtra("email",email);
                i.putExtra("password",password);
                startActivity(i);

            }
        });

        continueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMainLogin) {
                    Intent i = new Intent(view.getContext(), HomepageActivity.class);
                    startActivity(i);
                }
                finish();

            }
        });

    }


}
