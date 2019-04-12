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


/**
 * A simple {@link Fragment} subclass.
 */
public class EarthquakeListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View v;

    public EarthquakeListFragment() {
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
        if (Application.earthquakes.isEmpty()) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            adapter = new EarthquakeAdapter(this.getActivity(), Application.earthquakes);
            recyclerView.setAdapter(adapter);
        }
    }
}
