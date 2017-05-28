package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.Toast;

import tr.com.berkaytutal.beslenmedanismani.HomepageActivity;
import tr.com.berkaytutal.beslenmedanismani.LoginActivity;
import tr.com.berkaytutal.beslenmedanismani.R;
import tr.com.berkaytutal.beslenmedanismani.SearchFilterActivity;

public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    protected FloatingActionButton filterButton;
    protected SearchView searchView;


    @Override
    public void setContentView(@LayoutRes int layoutID) {
        super.setContentView(R.layout.activity_base_drawer);
        onCreateDrawer(layoutID);

        searchView = (SearchView) findViewById(R.id.searchView);
    }

    protected void onCreateDrawer(@LayoutRes int layoutID) {


        ViewStub stub = (ViewStub) this.findViewById(R.id.myViewStub);
        stub.setLayoutResource(layoutID);
//        stub.setVisibility(View.VISIBLE);
        View inflated = stub.inflate();
//inflated.setVisibility(View.VISIBLE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        filterButton = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_drawer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.appBarProfileButton) {
            Toast.makeText(getApplicationContext(),"Profiiiiil",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.appBarLogoutButton) {
            Toast.makeText(getApplicationContext(),"Logout Yapıldı",Toast.LENGTH_SHORT).show();
            SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.clear();
            edit.putString("userEmail", "");
            edit.putString("userPass", "");
            edit.commit();

            ((GlobalVariables)getApplicationContext()).setUserDataPOJO(null);

            Intent i = new Intent(this, HomepageActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setFilterButtonVisibility(boolean visibility) {
        if (visibility) {
            filterButton.setVisibility(View.VISIBLE);
        } else {
            filterButton.setVisibility(View.GONE);
        }

    }

    protected void setFilterButtonListener(int type){
        if(type == PublicVariables.FILTER_BUTTON_TYPE_PROGRAM)
        {
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(),SearchFilterActivity.class);
                    startActivity(intent);
//                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);



                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        else if(type == PublicVariables.FILTER_BUTTON_TYPE_USER){
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //TODO buraya özel searchfilter eklenecek

                    Intent intent = new Intent(getApplicationContext(),SearchFilterActivity.class);
                    startActivity(intent);
//                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);



                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    }


}

