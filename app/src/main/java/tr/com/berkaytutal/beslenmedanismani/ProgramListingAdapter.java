package tr.com.berkaytutal.beslenmedanismani;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by berka on 15.05.2017.
 */

public class ProgramListingAdapter extends BaseAdapter{

    private LayoutInflater li;
    private ArrayList<Integer> list;



    public ProgramListingAdapter(Activity activity, ArrayList<Integer> arrayList) {
        li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = arrayList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listeElemani = li.inflate(R.layout.listing_adapter_program,null);


        return listeElemani;
    }
}
