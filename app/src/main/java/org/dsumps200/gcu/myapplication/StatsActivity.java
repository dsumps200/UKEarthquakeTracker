//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView earthquakeDetailsTitle, earthquakeLocation, earthquakeDateTime, earthquakeMagnitude, earthquakeDepth, earthquakeLatitude, earthquakeLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_stats);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stats_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);

        earthquakeDetailsTitle = findViewById(R.id.earthquakeDetailsTitle);
        earthquakeLocation = findViewById(R.id.earthquakeLocation);
        earthquakeDateTime = findViewById(R.id.earthquakeDateTime);
        earthquakeMagnitude = findViewById(R.id.earthquakeMagnitude);
        earthquakeDepth = findViewById(R.id.earthquakeDepth);
        earthquakeLatitude = findViewById(R.id.earthquakeLatitude);
        earthquakeLongitude = findViewById(R.id.earthquakeLongitude);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        earthquakeDetailsTitle.setText(item);
        Earthquake earthquake = new Earthquake(null, null, 0, 0, 0, 0);
        switch (parent.getSelectedItemPosition()) {
            case 0:
                earthquake = getNorth();
                break;
            case 1:
                earthquake = getEast();
                break;
            case 2:
                earthquake = getSouth();
                break;
            case 3:
                earthquake = getWest();
                break;
            case 4:
                earthquake = getHighMagnitude();
                break;
            case 5:
                earthquake = getHighDepth();
                break;
            case 6:
                earthquake = getLowDepth();
                break;
        }
        earthquakeLocation.setText(earthquake.getLocation());
        earthquakeDateTime.setText(earthquake.getDateTime());
        earthquakeMagnitude.setText(Float.toString(earthquake.getMagnitude()));
        earthquakeDepth.setText(Integer.toString(earthquake.getDepth()));
        earthquakeLatitude.setText(Float.toString(earthquake.getLatitude()));
        earthquakeLongitude.setText(Float.toString(earthquake.getLongitude()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
}
