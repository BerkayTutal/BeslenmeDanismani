package tr.com.berkaytutal.beslenmedanismani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.TrainerPOJO;

import static tr.com.berkaytutal.beslenmedanismani.R.id.programDetailCategory;

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

    private int programID;
    private ProgramPOJO program;
    private TrainerPOJO trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detail);

        programID = getIntent().getIntExtra("programID", 0);
        program = ((GlobalVariables) getApplicationContext()).getProgramByID(programID);
        trainer = ((GlobalVariables) getApplicationContext()).getUserByID(program.getTrainerID());

        programImageView = (ImageView) findViewById(R.id.programDetailImage);
        downloadButton = (Button) findViewById(R.id.programDetailDownload);
        startButton = (Button) findViewById(R.id.programDetailStart);
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

    private void goTrainerPage(){
        Intent i = new Intent(this,TrainerDetailPage.class);
        i.putExtra("userID",trainer.getUserID());
        startActivity(i);
    }
}
