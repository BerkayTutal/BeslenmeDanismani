package tr.com.berkaytutal.beslenmedanismani;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.ChestPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.DBHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.ExercisePOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.JSONParser;
import tr.com.berkaytutal.beslenmedanismani.Utils.NotChestPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class ProgramDetailActivity extends BaseDrawerActivity {

    private ImageView programImageView;
    private Button downloadButton;
    private Button startButton;
    private TextView programTitle;
    private TextView programCategory;
    private TextView programHardness;
    private TextView programDescription;
    private ImageView trainerImage;
    private TextView trainerName;
    private Button trainerSeeMore;
    private LinearLayout trainerLayout;

    private LinearLayout boughtProgramLinearLayout;
    private Button buyThisProgramButton;
    private Button deleteProgramButton;


    private int programID;
    private ProgramPOJO program;
    private TrainerPOJO trainer;
    private UserDataPOJO user;

    private MenuItem reportButton;
    private boolean reportButtonVisibility = false;

    private View.OnClickListener downloadOnClickListener;

    protected boolean isWorkoutButton = false;
    private LinearLayout commentsLinearLayout;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.appBarReportButton) {
            Toast.makeText(getApplicationContext(), "report", Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog_report);

            Button cancelButton = (Button) dialog.findViewById(R.id.dialogReportCancelButton);
            Button reportButton = (Button) dialog.findViewById(R.id.dialogReportYesButton);
            final EditText reasonEditText = (EditText) dialog.findViewById(R.id.dialogReportEditText);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            reportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String reason = reasonEditText.getText().toString();
                    program.getProgramID();
                    program.getTrainerID();
                    user.getUser_ID();
                    Toast.makeText(view.getContext(),reason,Toast.LENGTH_SHORT).show();

                    //TODO buraya async yazılacak rapor atması için
                    dialog.dismiss();
                }
            });
            dialog.setCanceledOnTouchOutside(true);

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        reportButton = menu.findItem(R.id.appBarReportButton);
        reportButton.setVisible(reportButtonVisibility);


        return super.onPrepareOptionsMenu(menu);
    }
//TODO degistiyse tekrar yapilacak


    @Override
    protected void onResume() {
        super.onResume();
        buyThisProgramButton = (Button) findViewById(R.id.programBuyButton);
        boughtProgramLinearLayout = (LinearLayout) findViewById(R.id.boughtProgramLinearLayout);
        deleteProgramButton = (Button) findViewById(R.id.programDetailDelete);
        commentsLinearLayout = (LinearLayout) findViewById(R.id.programDetailCommentLinearLayout);

        commentsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),ProgramCommentsActivity.class);
                i.putExtra("programID",programID);
                startActivity(i);
            }
        });


        user = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();
        if (user != null) {
            program = user.getProgramByID(programID);

        }


        if (program == null) {
            buyThisProgramButton.setVisibility(View.VISIBLE);
            program = ((GlobalVariables) getApplicationContext()).getProgramByID(programID);
        } else {
            boughtProgramLinearLayout.setVisibility(View.VISIBLE);

            reportButtonVisibility = true;

        }
        trainer = ((GlobalVariables) getApplicationContext()).getUserByID(program.getTrainerID());
        downloadButton = (Button) findViewById(R.id.programDetailDownload);
        startButton = (Button) findViewById(R.id.programDetailStart);

        downloadOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isWorkoutButton = false;
                Toast.makeText(getApplicationContext(), "indirme başladı", Toast.LENGTH_SHORT).show();
                downloadProgram();


            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // program = ((GlobalVariables) getApplicationContext()).getProgramByID(programID);

                if (program.getExercisez() == null) {
                    isWorkoutButton = true;

                    Toast.makeText(getApplicationContext(), "indirme başladı", Toast.LENGTH_SHORT).show();
                    downloadProgram();
                } else {
                    isWorkoutButton = false;
                    startWorkout();
                }


            }
        });

        downloadButton.setOnClickListener(downloadOnClickListener);

        deleteProgramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.deleteProgramExercisez(program);
                ((GlobalVariables) getApplicationContext()).setUserDataPOJO(user);
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.updateUser(user);
                activateDownloadButton();


                Toast.makeText(getApplicationContext(), "silindi", Toast.LENGTH_SHORT).show();
            }
        });


        if (program.getExercisez() != null) {
            disableDownloadButton();
        }


        programImageView = (ImageView) findViewById(R.id.programDetailImage);

        programTitle = (TextView) findViewById(R.id.programDetailTitle);
        programCategory = (TextView) findViewById(R.id.programDetailCategory);
        programHardness = (TextView) findViewById(R.id.programDetailHardness);
        programDescription = (TextView) findViewById(R.id.programDetailDescription);

        trainerLayout = (LinearLayout) findViewById(R.id.programDetailTrainerLayout);
        trainerImage = (ImageView) findViewById(R.id.programDetailTrainerImage);
        trainerName = (TextView) findViewById(R.id.programDetailTrainerName);
        trainerSeeMore = (Button) findViewById(R.id.programDetailTrainerMore);

        programImageView.setImageBitmap(program.getProgramPhoto());
        programTitle.setText(program.getProgramTitle());
        programCategory.setText(program.getProgramSpec());
        programHardness.setText(program.getDifficulty());
        programDescription.setText(program.getProgramDescription());

        trainerImage.setImageBitmap(trainer.getPhoto());
        trainerName.setText(trainer.getName() + " " + trainer.getSurname());
        trainerSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTrainerPage();
            }
        });
        trainerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTrainerPage();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detail);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        programID = getIntent().getIntExtra("programID", 0);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


    private void goTrainerPage() {
        Intent i = new Intent(this, TrainerDetailPage.class);
        i.putExtra("userID", trainer.getUserID());
        startActivity(i);

    }

    private void downloadProgram() {
        MyAsyncClass async = new MyAsyncClass();
        async.execute("test");


    }

    protected void startWorkout() {
        Intent i = new Intent(getApplicationContext(), ProgramOverviewActivity.class);
        i.putExtra("programID", programID);
        startActivity(i);
    }

    private void activateDownloadButton() {
        downloadButton.setClickable(true);
        downloadButton.setText(getResources().getString(R.string.indir));
        downloadButton.setBackgroundColor(getResources().getColor(R.color.accent));
        downloadButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.download), null, null, null);
        downloadButton.setOnClickListener(downloadOnClickListener);
        deleteProgramButton.setVisibility(View.GONE);

    }

    private void disableDownloadButton() {
        downloadButton.setClickable(false);
        downloadButton.setText(getResources().getString(R.string.indirildi));
        downloadButton.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        downloadButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.check), null, null, null);
        downloadButton.setOnClickListener(null);
        deleteProgramButton.setVisibility(View.VISIBLE);

    }

    private class MyAsyncClass extends AsyncTask {

        ArrayList<ExercisePOJO> exercisePOJOs = new ArrayList<>();
        JSONArray jsonArray;


        @Override
        protected Object doInBackground(Object[] objects) {

            JSONParser jsonParser = new JSONParser();
            jsonArray = jsonParser.getJSONArrayFromUrl(PublicVariables.getProgramDetailsURL + program.getProgramID() + "/" + user.getUser_ID());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = new JSONObject();
                try {
                    json = (JSONObject) jsonArray.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String description = null;
                String exerciseType = null;
                int exercises_ID = 0;
                String name = null;
                int orderExercise = 0;
                byte[] photo1 = null;
                byte[] photo2 = null;
                int restTime = 0;
                String title = null;
                byte[] video = null;
                Integer circleID = null;
                Integer circleCount = null;


                try {
                    exerciseType = json.getString("exerciseType");
                    description = json.getString("description");
                    exercises_ID = json.getInt("exercises_ID");
                    name = json.getString("name");
                    orderExercise = json.getInt("orderExercise");
                    restTime = json.getInt("restTime");
                    title = json.getString("tittle");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    circleID = json.getInt("circleExercise_ID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    circleCount = json.getInt("circleExercise_Repeat");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    photo1 = Base64.decode(json.getString("photo1"), Base64.DEFAULT);
//                    byte[] imageByte = Base64.decode(json.getString("photo1"), Base64.DEFAULT);
//                    photo1 = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    photo2 = Base64.decode(json.getString("photo2"), Base64.DEFAULT);
//                    byte[] imageByte = Base64.decode(json.getString("photo2"), Base64.DEFAULT);
//                    photo2 = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (PublicVariables.CHEST.equals(exerciseType)) {

                    int agirlik = 0;
                    int setSayisi = 0;
                    int tekrarSayisi = 0;

                    try {
                        agirlik = json.getInt("agirlik");
                        setSayisi = json.getInt("setSayisi");
                        tekrarSayisi = json.getInt("tekrarSayisi");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ChestPOJO chestPOJO = new ChestPOJO(description, exerciseType, exercises_ID, name, orderExercise, photo1, photo2, restTime, title, video, agirlik, setSayisi, tekrarSayisi, circleID, circleCount);
                    exercisePOJOs.add(chestPOJO);


                } else {

                    int exerciseTime = 0;


                    try {

                        exerciseTime = json.getInt("exerciseTime");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NotChestPOJO notChestPOJO = new NotChestPOJO(description, exerciseType, exercises_ID, name, orderExercise, photo1, photo2, restTime, title, video, exerciseTime, circleID, circleCount);
                    exercisePOJOs.add(notChestPOJO);


                }


            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(getApplicationContext(), "indirme tamamlandı", Toast.LENGTH_SHORT).show();
            program.setExercisez(exercisePOJOs);
            user.setProgramByID(program);
            ((GlobalVariables) getApplicationContext()).setUserDataPOJO(user);
            DBHelper dbhelper = new DBHelper(getApplicationContext());
            dbhelper.updateUser(user);
            disableDownloadButton();


            if (isWorkoutButton) {
                startWorkout();
            }


            super.onPostExecute(o);
        }
    }
}
