package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramOverviewAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.ExercisePOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class WorkoutIntroActivity extends BaseDrawerActivity {

    private int programID;
    private ProgramPOJO program;
    private UserDataPOJO user;
    private ArrayList<ExercisePOJO> exercisePOJOs;

    private ListView listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_intro);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

         listview = (ListView) findViewById(R.id.workoutIntroListview);


        programID = getIntent().getIntExtra("programID", 0);
        user = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();
        program = user.getProgramByID(programID);

        exercisePOJOs = program.getExercisez();

        ProgramOverviewAdapter adapter = new ProgramOverviewAdapter(this,exercisePOJOs);
        listview.setAdapter(adapter);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

}
