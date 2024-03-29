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

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_info);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent (AboutActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                    break;
                case R.id.navigation_map:
                    Intent map = new Intent (AboutActivity.this, MapActivity.class);
                    startActivity(map);
                    finish();
                    break;
                case R.id.navigation_stats:
                    Intent stats = new Intent (AboutActivity.this, StatsActivity.class);
                    startActivity(stats);
                    finish();
                    break;
                case R.id.navigation_search:
                    Intent search = new Intent (AboutActivity.this, SearchActivity.class);
                    startActivity(search);
                    finish();
                    break;
                case R.id.navigation_info:
                    return true;
            }
            return false;
        }
    };

}
