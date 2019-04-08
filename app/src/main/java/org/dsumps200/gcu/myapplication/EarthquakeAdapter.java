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

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.ViewHolder> {

    private ArrayList<Earthquake> earthquakes;

    public EarthquakeAdapter (Context context, ArrayList<Earthquake> earthquakes) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @NonNull
    @Override
    public EarthquakeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(earthquakes.get(i));
        viewHolder.location.setText(earthquakes.get(i).getLocation());
        viewHolder.dateTime.setText(earthquakes.get(i).getDateTime());
        float magnitude = earthquakes.get(i).getMagnitude();
        viewHolder.magnitudeNo.setText(Float.toString(magnitude));
        if (magnitude>=4.0) {
            viewHolder.magnitudeCircle.setImageResource(R.drawable.ic_magnitude_high);
        } else if (magnitude<4.0 && magnitude>=2.0){
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
