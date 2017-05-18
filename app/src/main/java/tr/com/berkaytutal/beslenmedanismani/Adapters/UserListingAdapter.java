package tr.com.berkaytutal.beslenmedanismani.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.AllListingsActivity;
import tr.com.berkaytutal.beslenmedanismani.AllUsersActivity;
import tr.com.berkaytutal.beslenmedanismani.R;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;

/**
 * Created by berka on 15.05.2017.
 */

public class UserListingAdapter extends BaseAdapter {



    private LayoutInflater li;
    private ArrayList<Integer> list;
    private int type;
    private Context context;




    public UserListingAdapter(Activity activity, ArrayList<Integer> arrayList) {
        this.context = activity.getApplicationContext();

        li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = arrayList;
    }

    public UserListingAdapter(Activity activity, ArrayList<Integer> arrayList, int type) {

        this(activity,arrayList);
        this.type = type;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final View listeElemani = li.inflate(R.layout.listing_adapter_user,null);



        if(type == PublicVariables.TYPE_LISTINGS_ALL){
            listeElemani.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"TYPE 1",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(type == PublicVariables.TYPE_LISTINGS_HOMEPAGE){
            listeElemani.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), i +"" ,Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(view.getContext(),AllUsersActivity.class);
                    view.getContext().startActivity(intent);

                }
            });
        }

        return listeElemani;
    }
}
