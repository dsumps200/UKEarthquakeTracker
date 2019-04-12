//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EarthquakeDetailsFragment extends Fragment {


    public EarthquakeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earthquake_details, container, false);
    }

}
