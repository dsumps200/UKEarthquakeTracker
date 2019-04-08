package org.dsumps200.gcu.myapplication;

import java.util.ArrayList;

public class Application extends android.app.Application {

    public static ArrayList<Earthquake> earthquakes;

    @Override
    public void onCreate() {
        super.onCreate();

        earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("GLASGOW, SCOTLAND", "Mon, 3 Mar 2019 13:02:21", (float)1.3, 2, (float)24.3, (float)22.7));
        earthquakes.add(new Earthquake("GLASGOW, SCOTLAND", "Mon, 3 Mar 2019 13:02:21", (float)3.9, 2, (float)24.3, (float)22.7));
        earthquakes.add(new Earthquake("GLASGOW, SCOTLAND", "Mon, 3 Mar 2019 13:02:21", (float)6.1, 2, (float)24.3, (float)22.7));
        earthquakes.add(new Earthquake("GLASGOW, SCOTLAND", "Mon, 3 Mar 2019 13:02:21", (float)2.2, 2, (float)24.3, (float)22.7));
        earthquakes.add(new Earthquake("GLASGOW, SCOTLAND", "Mon, 3 Mar 2019 13:02:21", (float)0.5, 2, (float)24.3, (float)22.7));
    }
}
