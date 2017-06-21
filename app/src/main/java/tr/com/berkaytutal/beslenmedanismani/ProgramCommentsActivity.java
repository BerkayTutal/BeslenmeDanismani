package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.ProgramCommentsAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.CommentPOJO;

public class ProgramCommentsActivity extends AppCompatActivity {

    private int programID;
    private ListView programCommentsListView;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_comments);
        activity = this;
        context = this;

        programID = getIntent().getIntExtra("programID", 0);
        Toast.makeText(this, programID + " ", Toast.LENGTH_SHORT).show();

        programCommentsListView = (ListView) findViewById(R.id.programCommentsListView);


        MyAsyncClass async = new MyAsyncClass();
        async.execute("test");

        //TODO buraya bir adapter yazılacak ve comment içeriği ayarlanacak
        //+ comment yapma butonu eklenecek
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

            ProgramCommentsAdapter adapter = new ProgramCommentsAdapter(activity,comments);
            programCommentsListView.setAdapter(adapter);
            super.onPostExecute(s);
        }
    }
}
