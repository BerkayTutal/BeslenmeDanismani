package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Utils.BodyRatioPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.DBHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.JSONParser;
import tr.com.berkaytutal.beslenmedanismani.Utils.PasswordHashingMD5;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
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
    private boolean isTrainer = false;
    public static Activity loginActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


        loginActivity = this;


        emailEditText = (EditText) findViewById(R.id.emailAddressLoginScreen);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginScreen);
        registerButton = (Button) findViewById(R.id.registerButtonLoginScreen);
        loginButton = (Button) findViewById(R.id.loginButtonLoginScreen);
        continueTextView = (TextView) findViewById(R.id.continueWithoutLoginTextView);


        isMainLogin = getIntent().getBooleanExtra("isMainLogin", false);
        UserDataPOJO userDataPOJO = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();
        if (userDataPOJO != null && !isMainLogin) {

            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);

            finish();


        }
        if (isMainLogin) {


            SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
            String userEmail = userDetails.getString("userEmail", "");
            String userPass = userDetails.getString("userPass", "");

            if (!userEmail.equals("") && !userPass.equals("")) {
                email = userEmail;
                password = userPass;
                MyLoginAsync loginAsync = new MyLoginAsync();
                loginAsync.execute("test1");
            }


        }
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
                Intent i = new Intent(view.getContext(), RegisterActivity.class);
                i.putExtra("email", email);
                i.putExtra("password", password);
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


    private class MyLoginAsync extends AsyncTask {
        JSONObject jsonObject;


        @Override
        protected void onPostExecute(Object o) {

            super.onPostExecute(o);
            if (o.toString().equals("wrongLogin")) {
                Toast.makeText(getApplicationContext(), "Wrong Email or Password !", Toast.LENGTH_SHORT).show();

            } else {
                //Giris basarili...

                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putString("userEmail", email);
                edit.putString("userPass", password);
                edit.commit();
                Toast.makeText(getApplicationContext(), "Login details are saved..", Toast.LENGTH_LONG).show();
                if (isTrainer) {
                    Intent intent = new Intent(getApplicationContext(), MyProgramsActivity.class);
                    startActivity(intent);
                } else {
                    Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
                    startActivity(i);
                }


                finish();
            }
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            DBHelper dbhelper = new DBHelper(getApplicationContext());
            UserDataPOJO dbUser = dbhelper.getUser();

            boolean shouldIUpdate = false;
            if (dbUser != null) {
                shouldIUpdate = true;
            }

            JSONParser jsonParser = new JSONParser();
            jsonObject = jsonParser.getJSONObjectFromUrl(PublicVariables.loginURL + email + "/" + PasswordHashingMD5.md5(password));


            if (jsonObject == null) {
                return "wrongLogin";
            } else {
                Log.i("login", jsonObject.toString());


                try {
                    int user_id = jsonObject.getInt("user_ID");
                    String name = jsonObject.getString("name");
                    String surname = jsonObject.getString("surname");
                    String email = jsonObject.getString("email");
                    String sex = jsonObject.getString("sex");
                    String birthday = jsonObject.getString("birthday");

                    isTrainer = jsonObject.getBoolean("isTrainer");
                    JSONArray bodyRatiosJSONArr = jsonObject.getJSONArray("bodyRatesList");

                    ArrayList<ProgramPOJO> myPrograms = new ArrayList<>();
                    ArrayList<BodyRatioPOJO> bodyRatios = new ArrayList<>();

                    JSONArray programArray = jsonObject.getJSONArray("userPrograms");

                    for (int j = 0; j < programArray.length(); j++) {
                        JSONObject program = (JSONObject) programArray.get(j);
                        String programDiff = program.getString("programDiffName");
                        byte[] imageByte = Base64.decode(program.getString("programPhoto"), Base64.DEFAULT);
//                        Bitmap programPhoto = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                        String programSpecName = program.getString("programSpecName");
                        String programTittle = program.getString("programTittle");
                        int program_ID = program.getInt("program_ID");
                        String trainerName = program.getString("trainerName");
                        String trainerSurname = program.getString("trainerName");
                        int trainer_id = program.getInt("trainer_ID");
                        String programDescription = program.getString("programDescription");

                        ProgramPOJO myProgram = new ProgramPOJO(programDiff, imageByte, programSpecName, programTittle, programDescription, program_ID, trainer_id, trainerName, trainerSurname);
                        myPrograms.add(myProgram);
                    }


                    for (int j = 0; j < bodyRatiosJSONArr.length(); j++) {
                        JSONObject jsonObj = (JSONObject) bodyRatiosJSONArr.get(j);
                        String date = jsonObj.getString("date");
                        float fatRate = jsonObj.getLong("fatRate");
                        float muscleRate = jsonObj.getLong("muscleRate");
                        float waterRate = jsonObj.getLong("waterRate");
                        float weight = jsonObj.getLong("weight");
                        int tall = jsonObj.getInt("tall");
                        int user_ID = jsonObj.getInt("user_ID");
                        bodyRatios.add(new BodyRatioPOJO(date, fatRate, muscleRate, tall, user_ID, waterRate, weight));


                    }


                    if (dbUser == null) {
                        dbUser = new UserDataPOJO(user_id, name, surname, email, sex, birthday, myPrograms);
                    } else {
                        for (ProgramPOJO program : myPrograms) {
                            dbUser.insertProgram(program);
                        }
                        dbUser.deleteProgramsExcept(myPrograms);
                        ArrayList<ProgramPOJO> updatedPrograms = dbUser.getMyPrograms();
                        dbUser = new UserDataPOJO(user_id, name, surname, email, sex, birthday, updatedPrograms);
                    }

                    dbUser.setBodyRatios(bodyRatios);
                    dbUser.setTrainer(isTrainer);


                    ((GlobalVariables) getApplicationContext()).setUserDataPOJO(dbUser);
                    if (shouldIUpdate) {
                        dbhelper.updateUser(dbUser);
                    } else {
                        dbhelper.insertUser(dbUser);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return "success";
        }
    }

}
