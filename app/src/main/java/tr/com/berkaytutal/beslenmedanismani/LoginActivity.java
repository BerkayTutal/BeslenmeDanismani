package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.JSONParser;
import tr.com.berkaytutal.beslenmedanismani.Utils.PasswordHashingMD5;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

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

                MyLoginAsync loginAsync = new MyLoginAsync();
                loginAsync.execute("test2");

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

    class MyLoginAsync extends AsyncTask{
        JSONObject jsonObject;

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o.toString()=="wrongLogin"){
                Toast.makeText(getApplicationContext(), "Wrong Email or Password !", Toast.LENGTH_SHORT).show();
            }else{
                //Giris basarili...
            }
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.getJSONObjectFromUrl(PublicVariables.loginURL+email+"/"+PasswordHashingMD5.md5(password));
            if(jsonObject==null){
                return "wrongLogin";
            }else {


                try {
                    String user_id = jsonObject.getString("user_ID");
                    String name = jsonObject.getString("name");
                    String surname = jsonObject.getString("surname");
                    String email = jsonObject.getString("email");
                    String sex = jsonObject.getString("sex");
                    String birthday = jsonObject.getString("birthday");
                    String tall = jsonObject.getString("tall");
                    String weight = jsonObject.getString("weight");
                    String muscleRate = jsonObject.getString("muscleRate");
                    String fatRate = jsonObject.getString("fatRate");
                    String waterRate = jsonObject.getString("waterRate");

                    UserDataPOJO userDataPOJO = new UserDataPOJO(user_id,name,surname,email,sex,birthday,tall,weight,muscleRate,fatRate,waterRate);
                    ((GlobalVariables) getApplicationContext()).setUserDataPOJO(userDataPOJO);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

}
