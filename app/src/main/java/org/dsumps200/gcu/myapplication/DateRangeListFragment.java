//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateRangeListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View v;
    ArrayList<Earthquake> earthquakesWithinRange;

    public DateRangeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_earthquake_list, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = v.findViewById(R.id.earthquakeList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        earthquakesWithinRange = new ArrayList<>();
        adapter = new EarthquakeDateListAdapter(this.getActivity(), earthquakesWithinRange);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Earthquake> getDateRange(Date firstDate, Date secondDate) throws ParseException {
        for (int i = 0; i < Application.earthquakes.size(); i++) {
            Earthquake earthquake = Application.earthquakes.get(i);
            String dateString = earthquake.getDateTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
            Date date = simpleDateFormat.parse(dateString);
            if (!(date.before(firstDate) || date.after(secondDate))) {
                earthquakesWithinRange.add(earthquake);
            }
        }
        return earthquakesWithinRange;
    }

    public void refreshList() {
        adapter = new EarthquakeDateListAdapter(this.getActivity(), earthquakesWithinRange);
        recyclerView.setAdapter(adapter);
    }

}
