package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.JSONParser;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserPOJO;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //TODO adaptörlerdeki type'lar gereksiz

       MyAsyncClass asy = new MyAsyncClass();
        asy.execute("test");


    }


    class MyAsyncClass extends AsyncTask {
        JSONArray jsonArray;

        @Override
        protected Object doInBackground(Object[] objects) {


            ArrayList<ProgramPOJO> allPrograms = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();

            ArrayList<UserPOJO> allUsers = new ArrayList<>();

            jsonArray = jsonParser.getJSONFromUrl(PublicVariables.allProgramsURL);
            for (int i = 0; i < jsonArray.length(); i++) {


                ProgramPOJO program;
                try {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);
                    String diff = jobj.getString("programDiff");

                    byte[] imageByte = Base64.decode(jobj.getString("programPhoto"), Base64.DEFAULT);
                    Bitmap photo = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);


                    String programSpec = jobj.getString("programSpec");
                    String programTitle = jobj.getString("programTittle");
                    int programID = jobj.getInt("program_ID");
                    int trainerID = jobj.getInt("trainer_ID");
                    int userID = jobj.getInt("user_ID");


                    program = new ProgramPOJO(diff, photo, programSpec, programTitle, programID, trainerID, userID);

                    allPrograms.add(program);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setAllPrograms(allPrograms);

            jsonArray = jsonParser.getJSONFromUrl(PublicVariables.allUsersURL);
            for (int i = 0; i < jsonArray.length(); i++) {


                UserPOJO user;
                try {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);


                    byte[] imageByte = Base64.decode(jobj.getString("photo"), Base64.DEFAULT);
                    Bitmap photo = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

                    String birthday = jobj.getString("birthday");
                    int userID = jobj.getInt("id");
                    String name = jobj.getString("name");
                    String surname = jobj.getString("surname");
                    String sex = jobj.getString("sex");

                    user = new UserPOJO(name, surname, sex, photo, userID, birthday);
                    allUsers.add(user);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setAllUsers(allUsers);


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent i = new Intent(getApplication(), LoginActivity.class);
            i.putExtra("isMainLogin", true);
            startActivity(i);
            finish();
        }
    }


}
