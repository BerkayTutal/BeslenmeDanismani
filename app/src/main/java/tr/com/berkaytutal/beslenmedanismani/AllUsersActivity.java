package tr.com.berkaytutal.beslenmedanismani;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.com.berkaytutal.beslenmedanismani.Adapters.UserListingAdapter;
import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;

public class AllUsersActivity extends BaseDrawerActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_listings);
        setFilterButtonVisibility(true);
        setFilterButtonListener(PublicVariables.FILTER_BUTTON_TYPE_USER);

        searchView.setVisibility(View.VISIBLE);
//        searchView.setIconified(false);

        searchView.setOnQueryTextListener(this);



        listView = (ListView) findViewById(R.id.allProgramsListView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));

//        swipeRefreshLayout.setRefreshing(true);


        ArrayList arr = new ArrayList();
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(1);


        UserListingAdapter ula = new UserListingAdapter(this, arr, PublicVariables.TYPE_LISTINGS_ALL);
        listView.setAdapter(ula);

    }

    @Override
    public void onRefresh() {

        Toast.makeText(this, "Refresh yapıldı", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        Toast.makeText(this,query,Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
