package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramCommentsAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.CommentPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.DataSenderHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.ProgramPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class ProgramCommentsActivity extends AppCompatActivity {

    private int programID;
    private ListView programCommentsListView;
    private Context context;
    private Activity activity;
    private FloatingActionButton commentFAB;
    private boolean isBought = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_comments);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        activity = this;
        context = this;

        programID = getIntent().getIntExtra("programID", 0);
        Toast.makeText(this, programID + " ", Toast.LENGTH_SHORT).show();

        programCommentsListView = (ListView) findViewById(R.id.programCommentsListView);
        commentFAB = (FloatingActionButton) findViewById(R.id.programCommentsFAB);
        UserDataPOJO user = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();
        final ProgramPOJO myProgram;
        if(user!=null){
         myProgram = ((GlobalVariables) getApplicationContext()).getUserDataPOJO().getProgramByID(programID);
            if (myProgram != null) {
                isBought = true;
            }
        }





        if (isBought == false) {
            commentFAB.setVisibility(View.GONE);
        }


        final Dialog commentDialog = new Dialog(this);
        commentDialog.setContentView(R.layout.custom_dialog_comment);
        final Spinner ratingSpinner = (Spinner) commentDialog.findViewById(R.id.commentRatingSpinner);
        final ArrayAdapter<CharSequence> commentRatingAdapter = ArrayAdapter.createFromResource(
                this, R.array.ratings, android.R.layout.simple_spinner_item);
        commentRatingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //TODO burada bir spinner hatası var
        ratingSpinner.setAdapter(commentRatingAdapter);


        final EditText commentEditText = (EditText) commentDialog.findViewById(R.id.dialogCommentEditText);
        Button cancelButton = (Button) commentDialog.findViewById(R.id.dialogCommentCancelButton);
        Button commentButton = (Button) commentDialog.findViewById(R.id.dialogCommentYesButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentDialog.dismiss();
            }
        });
        if (isBought) {
            commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//TODO buraya async yardır bi tane
                    int rating = Integer.parseInt(ratingSpinner.getSelectedItem().toString().replaceAll("[\\D]", ""));
                    String comment = commentEditText.getText().toString();
                    Toast.makeText(view.getContext(), "Your comment is sending", Toast.LENGTH_SHORT).show();
                    commentDialog.dismiss();

                    JSONObject jsonComment = new JSONObject();
                    try {
                        jsonComment.accumulate("program_ID", programID);
                        jsonComment.accumulate("user_ID",((GlobalVariables)getApplicationContext()).getUserDataPOJO().getUser_ID() );
                        jsonComment.accumulate("rating", rating);
                        jsonComment.accumulate("comment", comment);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MakeCommentAsync makeCommentAsync = new MakeCommentAsync();
                    makeCommentAsync.execute(jsonComment);

                }
            });
        }
        commentDialog.setCanceledOnTouchOutside(true);


        commentFAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                commentDialog.show();

            }
        });


        MyAsyncClass async = new MyAsyncClass();
        async.execute("test");

        //TODO buraya bir adapter yazılacak ve comment içeriği ayarlanacak
        //+ comment yapma butonu eklenecek
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private class MakeCommentAsync extends AsyncTask<JSONObject, String, String> {

        @Override
        protected String doInBackground(JSONObject... jsonObjects) {

            return DataSenderHelper.POST(PublicVariables.commentURL,jsonObjects[0]);

        }

        @Override
        protected void onPostExecute(String s) {

            if ("true".equals(s)) {
                Toast.makeText(context, "Your comment sent succesfully", Toast.LENGTH_SHORT).show();
            } else if ("false".equals(s)) {
                Toast.makeText(context, "You've already commented", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "A problem occured while sending", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(s);
        }
    }


    private class MyAsyncClass extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            //TODO loading dialog
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(context, "postExecute", Toast.LENGTH_SHORT).show();

            ArrayList<CommentPOJO> comments = new ArrayList<>();
            comments.add(null);
            comments.add(null);
            comments.add(null);
            comments.add(null);
            comments.add(null);
            comments.add(null);
            comments.add(null);
            comments.add(null);

            ProgramCommentsAdapter adapter = new ProgramCommentsAdapter(activity, comments);
            programCommentsListView.setAdapter(adapter);
            super.onPostExecute(s);
        }
    }
}
