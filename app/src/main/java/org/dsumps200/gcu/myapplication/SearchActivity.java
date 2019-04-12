//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstDatePicker, secondDatePicker;
    Button findDatesButton;
    DateRangeListFragment dateRangeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstDatePicker = findViewById(R.id.firstDatePicker);
        secondDatePicker = findViewById(R.id.secondDatePicker);
        findDatesButton = findViewById(R.id.findDatesButton);
        firstDatePicker.setOnClickListener(this);
        secondDatePicker.setOnClickListener(this);
        findDatesButton.setOnClickListener(this);
        dateRangeListFragment = (DateRangeListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_search);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent (SearchActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                    break;
                case R.id.navigation_map:
                    Intent map = new Intent (SearchActivity.this, MapActivity.class);
                    startActivity(map);
                    finish();
                    break;
                case R.id.navigation_stats:
                    Intent stats = new Intent (SearchActivity.this, StatsActivity.class);
                    startActivity(stats);
                    finish();
                    break;
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_info:
                    Intent info = new Intent (SearchActivity.this, AboutActivity.class);
                    startActivity(info);
                    finish();
                    break;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {

        Calendar mcurrentDate=Calendar.getInstance();
        int mYear=mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        switch (v.getId()) {
            case R.id.firstDatePicker:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        firstDatePicker.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.setTitle("Select Start Date");
                datePickerDialog.show();
                break;
            case R.id.secondDatePicker:
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        secondDatePicker.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog2.setTitle("Select End Date");
                datePickerDialog2.show();
                break;
            case R.id.findDatesButton:
                dateRangeListFragment.earthquakesWithinRange.clear();
                String firstDateString = firstDatePicker.getText().toString();
                String secondDateString = secondDatePicker.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date firstDate, secondDate;
                try {
                    firstDate = simpleDateFormat.parse(firstDateString);
                    secondDate = simpleDateFormat.parse(secondDateString);
                    dateRangeListFragment.getDateRange(firstDate, secondDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateRangeListFragment.refreshList();
                break;
        }
    }
}
