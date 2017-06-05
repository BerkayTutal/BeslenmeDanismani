package tr.com.berkaytutal.beslenmedanismani.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.R;
import tr.com.berkaytutal.beslenmedanismani.Utils.ExercisePOJO;

/**
 * Created by berka on 5.06.2017.
 */

public class ProgramOverviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ExercisePOJO> exercises;
    private LayoutInflater li;

    public ProgramOverviewAdapter(Activity activity, ArrayList<ExercisePOJO> exercises) {
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
        return exercises.get(i).getExercises_ID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        final View listeElemani = li.inflate(R.layout.listing_adapter_program_overview, null);
        final ExercisePOJO exercıse = exercises.get(i);


        ImageView image = (ImageView) listeElemani.findViewById(R.id.listingExerciseImage);
        TextView title = (TextView) listeElemani.findViewById(R.id.listingExerciseTitle);

        image.setImageBitmap(exercıse.getPhoto1());


        title.setText(exercıse.getName());


        return listeElemani;
    }
}
