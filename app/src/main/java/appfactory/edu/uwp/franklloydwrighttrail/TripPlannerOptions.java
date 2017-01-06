package appfactory.edu.uwp.franklloydwrighttrail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import io.realm.Realm;

/**
 * Created by sterl on 10/28/2016.
 */

public class TripPlannerOptions extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Button cont;

    private Switch breakfastSwitch;
    private Switch lunchSwitch;
    private Switch dinnerSwitch;

    private RelativeLayout breakfastContainer;
    private RelativeLayout lunchContainer;
    private RelativeLayout dinnerContainer;

    private boolean yesBreakfast = false;
    private boolean yesLunch = false;
    private boolean yesDinner = false;

    private boolean complete = true;

    private EditText timeBreakfast;
    private EditText timeLunch;
    private EditText timeDinner;

    private Realm realm;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, TripPlannerOptions.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_options);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.trip_options);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cont = (Button) findViewById(R.id.cont);
        initializeViews();

        realm = RealmController.getInstance().getRealm();

        initializeSwitches();
        //initializeContainers();

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCheck();
                if (complete) {
                    Intent intent = new Intent(TripPlannerOptions.this, TripPlannerTimeline.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void mealCheck(){
        if (yesBreakfast) {
            if (timeBreakfast.getText().toString().equals("") || Integer.parseInt(timeBreakfast.getText().toString()) > 121) {
                complete = false;
            } else {
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setBreakfastTime(Integer.parseInt(timeBreakfast.getText().toString()));
                realm.commitTransaction();
                complete = true;
            }
        } else {
            complete = true;
        }
        if (yesLunch) {
            if (timeLunch.getText().toString().equals("") || Integer.parseInt(timeLunch.getText().toString()) > 121) {
                complete = false;
            } else {
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setLunchTime(Integer.parseInt(timeLunch.getText().toString()));
                realm.commitTransaction();
                complete = true;
            }
        } else {
            complete = true;
        }
        if (yesDinner) {
            if (timeDinner.getText().toString().equals("") || Integer.parseInt(timeDinner.getText().toString()) > 121) {
                complete = false;
            } else {
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setDinnerTime(Integer.parseInt(timeDinner.getText().toString()));
                realm.commitTransaction();
                complete = true;
            }
        } else {
            complete = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }
    }

    /*
        Switches the appr
     */
    private boolean mealToggle(RelativeLayout mealContainer, Boolean yesMeal) {
        if (yesMeal) { //turn off
            mealContainer.setVisibility(View.GONE);
            return false;
        } else { //turn on
            mealContainer.setVisibility(View.VISIBLE);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_locations:
                break;
            case R.id.nav_trip_planner:
                break;
            //case R.id.nav_scrapbook:
            //    break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Easy Clump of all the used views
    private void initializeViews(){
        breakfastSwitch = (Switch) findViewById(R.id.breakfast_switch);
        lunchSwitch = (Switch) findViewById(R.id.lunch_switch);
        dinnerSwitch = (Switch) findViewById(R.id.dinner_switch);

        breakfastContainer = (RelativeLayout) findViewById(R.id.length_breakfast_container);
        lunchContainer = (RelativeLayout) findViewById(R.id.length_lunch_container);
        dinnerContainer = (RelativeLayout) findViewById(R.id.length_dinner_container);

        timeBreakfast = (EditText) findViewById(R.id.length_time_breakfast);
        timeLunch = (EditText) findViewById(R.id.length_time_lunch);
        timeDinner = (EditText) findViewById(R.id.length_time_dinner);
    }

    private void initializeSwitches(){
        breakfastSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yesBreakfast = mealToggle(breakfastContainer,yesBreakfast);
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setBreakfastTime(0);
                realm.commitTransaction();
            }
        });

        lunchSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yesLunch = mealToggle(lunchContainer,yesLunch);
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setLunchTime(0);
                realm.commitTransaction();
            }
        });

        dinnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                yesDinner = mealToggle(dinnerContainer,yesDinner);
                realm.beginTransaction();
                RealmController.getInstance().getTrip().setDinnerTime(0);
                realm.commitTransaction();
            }
        });
    }
/*
    private void initializeContainers(){
        breakfastContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        realm.beginTransaction();
                        RealmController.getInstance().getTrip().setBreakfastTime(2);
                        realm.commitTransaction();
                        complete = true;
            }
        });

        lunchContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        realm.beginTransaction();
                        RealmController.getInstance().getTrip().setLunchTime(2);
                        realm.commitTransaction();
                        complete = true;
            }
        });

        dinnerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        //Set time here
                        realm.beginTransaction();
                        RealmController.getInstance().getTrip().setDinnerTime(2);
                        realm.commitTransaction();
                        complete = true;
            }
        });

    }
    */
}
