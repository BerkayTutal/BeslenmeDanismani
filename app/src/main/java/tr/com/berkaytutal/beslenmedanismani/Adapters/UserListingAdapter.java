package tr.com.berkaytutal.beslenmedanismani.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.AllListingsActivity;
import tr.com.berkaytutal.beslenmedanismani.AllUsersActivity;
import tr.com.berkaytutal.beslenmedanismani.R;
import tr.com.berkaytutal.beslenmedanismani.TrainerDetailPage;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserPOJO;

/**
 * Created by berka on 15.05.2017.
 */

public class UserListingAdapter extends BaseAdapter {


    private LayoutInflater li;
    private ArrayList<UserPOJO> list;
    private Context context;


    public UserListingAdapter(Activity activity, ArrayList<UserPOJO> arrayList) {
        this.context = activity.getApplicationContext();

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final View listeElemani = li.inflate(R.layout.listing_adapter_user, null);
        UserPOJO user = list.get(i);

        ImageView image = (ImageView) listeElemani.findViewById(R.id.trainerListingImage);
        TextView trainerName = (TextView) listeElemani.findViewById(R.id.trainerNameText);
        trainerName.setText(user.getName() + " " + user.getSurname());


        listeElemani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), TrainerDetailPage.class);
                view.getContext().startActivity(intent);

            }
        });


        return listeElemani;
    }
}
