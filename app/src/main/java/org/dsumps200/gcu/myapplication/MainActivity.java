package org.dsumps200.gcu.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements EarthquakeAdapter.ItemClicked {

    //private TextView mTextMessage;
    TextView earthquakeLocation, earthquakeDateTime, earthquakeMagnitude, earthquakeDepth, earthquakeLatitude, earthquakeLongitude;
    LinearLayout listView, detailsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquakeLocation = findViewById(R.id.earthquakeLocation);
        earthquakeDateTime = findViewById(R.id.earthquakeDateTime);
        earthquakeMagnitude = findViewById(R.id.earthquakeMagnitude);
        earthquakeDepth = findViewById(R.id.earthquakeDepth);
        earthquakeLatitude = findViewById(R.id.earthquakeLatitude);
        earthquakeLongitude = findViewById(R.id.earthquakeLongitude);
        listView = findViewById(R.id.listView);
        detailsView = findViewById(R.id.detailsView);
        onItemClicked(0);
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

        detailsView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

    }
}
