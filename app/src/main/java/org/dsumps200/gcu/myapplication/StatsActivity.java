package org.dsumps200.gcu.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    TextView northLocation, northDateTime, northLatitude;
    TextView eastLocation, eastDateTime, eastLongitude;
    TextView southLocation, southDateTime, southLatitude;
    TextView westLocation, westDateTime, westLongitude;
    TextView magLocation, magDateTime, magnitude;
    TextView highDLocation, highDDateTime, highDDepth;
    TextView lowDLocation, lowDDateTime, lowDDepth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_stats);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ArrayList<Earthquake> getStats = getStats();
        northLocation = findViewById(R.id.northLocation);
        northLocation.setText(getStats.get(0).getLocation());
        northDateTime = findViewById(R.id.northDateTime);
        northDateTime.setText(getStats.get(0).getDateTime());
        northLatitude = findViewById(R.id.northLatitude);
        float nLat = getStats.get(0).getLatitude();
        northLatitude.setText("Latitude: " + Float.toString(nLat));
        eastLocation = findViewById(R.id.eastLocation);
        eastLocation.setText(getStats.get(1).getLocation());
        eastDateTime = findViewById(R.id.eastDateTime);
        eastDateTime.setText(getStats.get(1).getDateTime());
        eastLongitude = findViewById(R.id.eastLongitude);
        float eLong = getStats.get(1).getLongitude();
        eastLongitude.setText("Longitude: " + Float.toString(eLong));
        southLocation = findViewById(R.id.southLocation);
        southLocation.setText(getStats.get(2).getLocation());
        southDateTime = findViewById(R.id.southDateTime);
        southDateTime.setText(getStats.get(2).getDateTime());
        southLatitude = findViewById(R.id.southLatitude);
        float sLat = getStats.get(2).getLatitude();
        southLatitude.setText("Latitude: " + Float.toString(sLat));
        westLocation = findViewById(R.id.westLocation);
        westLocation.setText(getStats.get(3).getLocation());
        westDateTime = findViewById(R.id.westDateTime);
        westDateTime.setText(getStats.get(3).getDateTime());
        westLongitude = findViewById(R.id.westLongitude);
        float wLong = getStats.get(3).getLongitude();
        westLongitude.setText("Longitude: " + Float.toString(wLong));
        magLocation = findViewById(R.id.magLocation);
        magLocation.setText(getStats.get(4).getLocation());
        magDateTime = findViewById(R.id.magDateTime);
        magDateTime.setText(getStats.get(4).getDateTime());
        magnitude = findViewById(R.id.magnitude);
        float mag = getStats.get(4).getMagnitude();
        magnitude.setText("Magnitude: " + Float.toString(mag));
        highDLocation = findViewById(R.id.highDLocation);
        highDLocation.setText(getStats.get(5).getLocation());
        highDDateTime = findViewById(R.id.highDDateTime);
        highDDateTime.setText(getStats.get(5).getDateTime());
        highDDepth = findViewById(R.id.highDDepth);
        float highDepth = getStats.get(5).getDepth();
        highDDepth.setText("Depth: " + Float.toString(highDepth));
        lowDLocation = findViewById(R.id.lowDLocation);
        lowDLocation.setText(getStats.get(6).getLocation());
        lowDDateTime = findViewById(R.id.lowDDateTime);
        lowDDateTime.setText(getStats.get(6).getDateTime());
        lowDDepth = findViewById(R.id.lowDDepth);
        float lowDepth = getStats.get(6).getDepth();
        lowDDepth.setText("Depth: " + Float.toString(lowDepth));
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

    private Earthquake getNorth() {
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

    private Earthquake getEast() {
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

    private Earthquake getSouth() {
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

    private Earthquake getWest() {
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

    private Earthquake getHighMagnitude() {
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

    private Earthquake getLowDepth() {
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

    private Earthquake getHighDepth() {
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

    public ArrayList<Earthquake> getStats() {
        Earthquake north = getNorth();
        Earthquake east = getEast();
        Earthquake south = getSouth();
        Earthquake west = getWest();
        Earthquake highM = getHighMagnitude();
        Earthquake highD = getHighDepth();
        Earthquake lowD = getLowDepth();
        ArrayList<Earthquake> statsList = new ArrayList<>();
        statsList.add(north);
        statsList.add(east);
        statsList.add(south);
        statsList.add(west);
        statsList.add(highM);
        statsList.add(highD);
        statsList.add(lowD);
        return statsList;
    }
}
