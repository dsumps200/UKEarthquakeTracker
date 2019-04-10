package org.dsumps200.gcu.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EarthquakeAdapter.ItemClicked {

    //private TextView mTextMessage;
    TextView earthquakeLocation, earthquakeDateTime, earthquakeMagnitude, earthquakeDepth, earthquakeLatitude, earthquakeLongitude;
    LinearLayout listView, detailsView;

    @Override
    protected void onSaveInstanceState(Bundle b) {
        if (getString(R.string.screen_size).equals("phone")) {
            int detailsVis = detailsView.getVisibility();
            int listVis = listView.getVisibility();
            b.putInt("detailsVis", detailsVis);
            b.putInt("listVis", listVis);
        }
        ArrayList<String> detailsData = new ArrayList<>();
        detailsData.add(earthquakeLocation.getText().toString());
        detailsData.add(earthquakeDateTime.getText().toString());
        detailsData.add(earthquakeMagnitude.getText().toString());
        detailsData.add(earthquakeDepth.getText().toString());
        detailsData.add(earthquakeLatitude.getText().toString());
        detailsData.add(earthquakeLongitude.getText().toString());
        b.putStringArrayList("detailsData", detailsData);
        super.onSaveInstanceState(b);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int detailsVis = savedInstanceState.getInt("detailsVis");
        int listVis = savedInstanceState.getInt("listVis");
        ArrayList<String> detailsData = savedInstanceState.getStringArrayList("detailsData");
        earthquakeLocation.setText(detailsData.get(0));
        earthquakeDateTime.setText(detailsData.get(1));
        earthquakeMagnitude.setText(detailsData.get(2));
        earthquakeDepth.setText(detailsData.get(3));
        earthquakeLatitude.setText(detailsData.get(4));
        earthquakeLongitude.setText(detailsData.get(5));
        if (getString(R.string.screen_size).equals("phone")) {
            detailsView.setVisibility(detailsVis);
            listView.setVisibility(listVis);
        }
        if (getString(R.string.screen_size).equals("tablet") && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            detailsView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
        if (listVis == View.GONE && getString(R.string.screen_size).equals("phone")) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        earthquakeLocation = findViewById(R.id.earthquakeLocation);
        earthquakeDateTime = findViewById(R.id.earthquakeDateTime);
        earthquakeMagnitude = findViewById(R.id.earthquakeMagnitude);
        earthquakeDepth = findViewById(R.id.earthquakeDepth);
        earthquakeLatitude = findViewById(R.id.earthquakeLatitude);
        earthquakeLongitude = findViewById(R.id.earthquakeLongitude);
        listView = findViewById(R.id.listView);
        detailsView = findViewById(R.id.detailsView);
        if (getString(R.string.screen_size).equals("tablet") && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onItemClicked(0);
        }
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onSupportNavigateUp() {
        detailsView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onItemClicked(int i) {

        earthquakeLocation.setText(Application.earthquakes.get(i).getLocation());
        earthquakeDateTime.setText(Application.earthquakes.get(i).getDateTime());
        earthquakeMagnitude.setText(Float.toString(Application.earthquakes.get(i).getMagnitude()));
        earthquakeDepth.setText(Integer.toString(Application.earthquakes.get(i).getDepth()));
        earthquakeLatitude.setText(Float.toString(Application.earthquakes.get(i).getLatitude()));
        earthquakeLongitude.setText(Float.toString(Application.earthquakes.get(i).getLongitude()));

        if (getString(R.string.screen_size).equals("phone") || getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            detailsView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
