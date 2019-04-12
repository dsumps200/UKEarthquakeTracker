//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeDateListAdapter extends RecyclerView.Adapter<EarthquakeDateListAdapter.ViewHolder> {

    private ArrayList<Earthquake> earthquakes;

    public EarthquakeDateListAdapter (Context context, ArrayList<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView location, dateTime, magnitudeNo;
        ImageView magnitudeCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.location);
            dateTime = itemView.findViewById(R.id.dateTime);
            magnitudeNo = itemView.findViewById(R.id.magnitudeNo);
            magnitudeCircle = itemView.findViewById(R.id.magnitudeCircle);
        }
    }

    @NonNull
    @Override
    public EarthquakeDateListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeDateListAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(earthquakes.get(i));
        viewHolder.location.setText(earthquakes.get(i).getLocation());
        viewHolder.dateTime.setText(earthquakes.get(i).getDateTime());
        float magnitude = earthquakes.get(i).getMagnitude();
        viewHolder.magnitudeNo.setText(Float.toString(magnitude));
        if (magnitude>=2.5) {
            viewHolder.magnitudeCircle.setImageResource(R.drawable.ic_magnitude_high);
        } else if (magnitude<2.5 && magnitude>=1.5){
            viewHolder.magnitudeCircle.setImageResource(R.drawable.ic_magnitude_medium);
        } else {
            viewHolder.magnitudeCircle.setImageResource(R.drawable.ic_magnitude_low);
        }
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }
}