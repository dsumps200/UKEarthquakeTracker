package org.dsumps200.gcu.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_stats);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent (StatsActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                case R.id.navigation_map:
                    Intent map = new Intent (StatsActivity.this, MapActivity.class);
                    startActivity(map);
                    finish();
                case R.id.navigation_stats:
                    return true;
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_info:
                    Intent info = new Intent (StatsActivity.this, AboutActivity.class);
                    startActivity(info);
                    finish();
            }
            return false;
        }
    };

    public Earthquake getNorth() {
        float currentLat = -90;
        Earthquake northEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            float latitude = earthquake.getLatitude();
            if (latitude > currentLat) {
                currentLat = latitude;
                northEarthquake = earthquake;
            }
        } return northEarthquake;
    }

    public Earthquake getEast() {
        float currentLong = -1000;
        Earthquake eastEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            float longitude = earthquake.getLongitude();
            if (longitude > currentLong) {
                currentLong = longitude;
                eastEarthquake = earthquake;
            }
        } return eastEarthquake;
    }

    public Earthquake getSouth() {
        float currentLat = 90;
        Earthquake southEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            float latitude = earthquake.getLatitude();
            if (latitude < currentLat) {
                currentLat = latitude;
                southEarthquake = earthquake;
            }
        } return southEarthquake;
    }

    public Earthquake getWest() {
        float currentLong = 1000;
        Earthquake westEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            float longitude = earthquake.getLongitude();
            if (longitude < currentLong) {
                currentLong = longitude;
                westEarthquake = earthquake;
            }
        } return westEarthquake;
    }

    public Earthquake getHighMagnitude() {
        float currentMagnitude = -1000;
        Earthquake highMagEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            float magnitude = earthquake.getMagnitude();
            if (magnitude > currentMagnitude) {
                currentMagnitude = magnitude;
                highMagEarthquake = earthquake;
            }
        } return highMagEarthquake;
    }

    public Earthquake getLowDepth() {
        int currentDepth = 1000;
        Earthquake lowDepthEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            int depth = earthquake.getDepth();
            if (depth < currentDepth) {
                currentDepth = depth;
                lowDepthEarthquake = earthquake;
            }
        } return lowDepthEarthquake;
    }

    public Earthquake getHighDepth() {
        int currentDepth = -1000;
        Earthquake highDepthEarthquake = Application.earthquakes.get(0);
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            int depth = earthquake.getDepth();
            if (depth > currentDepth) {
                currentDepth = depth;
                highDepthEarthquake = earthquake;
            }
        } return highDepthEarthquake;
    }
}
