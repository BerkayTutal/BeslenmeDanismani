package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.JSONParser;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramCategoryPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramDifficultyPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


        //TODO adaptörlerdeki type'lar gereksiz

        MyAsyncClass asy = new MyAsyncClass();
        asy.execute("test");


    }


    private class MyAsyncClass extends AsyncTask {
        JSONArray jsonArray;

        @Override
        protected Object doInBackground(Object[] objects) {


            ArrayList<ProgramPOJO> allPrograms = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();

            ArrayList<TrainerPOJO> allUsers = new ArrayList<>();

            jsonArray = jsonParser.getJSONArrayFromUrl(PublicVariables.allProgramsURL);
            Log.i("allprograms",jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {


                ProgramPOJO program;
                try {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);
                    String diff = jobj.getString("programDiffName");

                    byte[] photo = Base64.decode(jobj.getString("programPhoto"), Base64.DEFAULT);
//                    byte[] imageByte = Base64.decode(jobj.getString("programPhoto"), Base64.DEFAULT);
//                    Bitmap photo = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

                    String programDescription = jobj.getString("programDescription");
                    String programSpec = jobj.getString("programSpecName");
                    String programTitle = jobj.getString("programTittle");
                    int programID = jobj.getInt("program_ID");
                    int trainerID = jobj.getInt("trainer_ID");
                    String trainerName = jobj.getString("trainerName");
                    String trainerSurname = jobj.getString("trainerSurname");


                    program = new ProgramPOJO(diff, photo, programSpec, programTitle, programDescription, programID, trainerID, trainerName, trainerSurname);

                    allPrograms.add(program);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setAllPrograms(allPrograms);

            jsonArray = jsonParser.getJSONArrayFromUrl(PublicVariables.allUsersURL);
            Log.i("alltrainers",jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {


                TrainerPOJO user;
                JSONObject jobj = null;
                try {
                    jobj = (JSONObject) jsonArray.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String sex = null;
                try {
                    sex = jobj.getString("sex");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                byte[] imageByte = null;
                try {
                    imageByte = Base64.decode(jobj.getString("photo"), Base64.DEFAULT);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bitmap photo = null;
                if(imageByte != null){
                     photo = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                }
                else {
                    if("M".equals(sex)){
                        photo = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile_man);
                    }
                    else{
                        photo = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile_woman);

                    }
                }
                try {





                    String birthday = jobj.getString("birthday");
                    int userID = jobj.getInt("id");
                    String name = jobj.getString("name");
                    String surname = jobj.getString("surname");


                    user = new TrainerPOJO(name, surname, sex, photo, userID, birthday);
                    allUsers.add(user);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setAllUsers(allUsers);

            ArrayList<ProgramCategoryPOJO> allCategories = new ArrayList<>();
            allCategories.add(new ProgramCategoryPOJO(PublicVariables.ALL_ID, "All"));

            jsonArray = jsonParser.getJSONArrayFromUrl(PublicVariables.programCategoriesURL);
            Log.i("programcategories",jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {


                ProgramCategoryPOJO category;
                try {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);



                    String name = jobj.getString("programSpec_Name");
                    int id = jobj.getInt("programSpec_ID");

                    category = new ProgramCategoryPOJO(id,name);
                    allCategories.add(category);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setProgramCategories(allCategories);

            ArrayList<ProgramDifficultyPOJO> allDifficulties = new ArrayList<>();
            allDifficulties.add(new ProgramDifficultyPOJO(PublicVariables.ALL_ID,"All"));

            jsonArray = jsonParser.getJSONArrayFromUrl(PublicVariables.programDiffURL);
            Log.i("alldifficulties",jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {


                ProgramDifficultyPOJO diff;
                try {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);



                    String name = jobj.getString("programDiff_Name");
                    int id = jobj.getInt("programDiff_ID");

                    diff = new ProgramDifficultyPOJO(id,name);
                    allDifficulties.add(diff);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            ((GlobalVariables) getApplicationContext()).setProgramDifficulties(allDifficulties);


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
