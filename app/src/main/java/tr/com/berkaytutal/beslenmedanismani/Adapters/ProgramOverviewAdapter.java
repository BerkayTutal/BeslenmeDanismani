package tr.com.berkaytutal.beslenmedanismani.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.R;
import tr.com.berkaytutal.beslenmedanismani.Utils.CirclePOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.CircleTekrarAbsPOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.ExercisePOJO;
import tr.com.berkaytutal.beslenmedanismani.Utils.TekrarPOJO;

import static android.view.View.GONE;

/**
 * Created by berka on 5.06.2017.
 */

public class ProgramOverviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CircleTekrarAbsPOJO> exercises;
    private LayoutInflater li;

    public ProgramOverviewAdapter(Activity activity, ArrayList<CircleTekrarAbsPOJO> exercises) {
        this.context = activity.getApplicationContext();
        this.exercises = exercises;

        li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int i) {
        return exercises.get(i);
    }

    @Override
    public long getItemId(int i) {
        int id = -1;

        if(exercises.get(i).isTekrar() && !exercises.get(i).isCircle()){
            id = ((TekrarPOJO)exercises.get(i)).getID();
        }
        else if(!exercises.get(i).isTekrar() && exercises.get(i).isCircle()) {
            id = ((CirclePOJO)exercises.get(i)).getId();
        }
        else if(!exercises.get(i).isTekrar() && !exercises.get(i).isCircle()) {
            id = ((ExercisePOJO)exercises.get(i)).getExercises_ID();
        }

        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        final View listeElemani = li.inflate(R.layout.listing_adapter_program_overview, null);



        ImageView image = (ImageView) listeElemani.findViewById(R.id.listingExerciseImage);
        TextView title = (TextView) listeElemani.findViewById(R.id.listingExerciseTitle);
        TextView tekrar = (TextView) listeElemani.findViewById(R.id.listingExerciseTekrar);

        Bitmap bitmap = null;
        String titleString = "";
        String tekrarString = "";


        if(exercises.get(i).isTekrar() && !exercises.get(i).isCircle()){
            bitmap = ((TekrarPOJO)exercises.get(i)).getPhoto1();
            titleString = ((TekrarPOJO)exercises.get(i)).getName();
            tekrarString = "x" + ((TekrarPOJO)exercises.get(i)).getTekrarCount();
        }

        else if(!exercises.get(i).isTekrar() && !exercises.get(i).isCircle()) {
            bitmap = ((ExercisePOJO)exercises.get(i)).getPhoto1();
            titleString = ((ExercisePOJO)exercises.get(i)).getName();
            tekrar.setVisibility(GONE);
        }



        image.setImageBitmap(bitmap);
        title.setText(titleString);
        tekrar.setText(tekrarString);


        return listeElemani;
    }
}
