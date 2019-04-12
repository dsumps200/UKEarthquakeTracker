//
// Name                 David Sumpster
// Student ID           S1518256
// Programme of Study   BSc Computing
//

package org.dsumps200.gcu.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Application extends android.app.Application {

    public static ArrayList<Earthquake> earthquakes;
    private final Handler handler = new Handler();

    private void refreshEarthquakes() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new ProcessEarthquakeRss().execute();
                refreshEarthquakes();
            }
        }, 300000);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        earthquakes = new ArrayList<>();
        new ProcessEarthquakeRss().execute();
        refreshEarthquakes();
    }

    public InputStream getRssStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public class ProcessEarthquakeRss extends AsyncTask<Void, Void, Exception> {

        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Exception doInBackground(Void... voids) {
            parseEarthquakes();
            return exception;
        }

        @Override
        protected void onPostExecute(Exception e) {
            super.onPostExecute(e);
        }

        private void parseEarthquakes() {
            try {
                URL url = new URL("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = factory.newPullParser();
                xmlPullParser.setInput(getRssStream(url), null);

                boolean isItem = false;
                int eventType = xmlPullParser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xmlPullParser.getName().equalsIgnoreCase("item")) {
                            isItem = true;
                        } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {
                            if (isItem) {
                                String descriptionSection = xmlPullParser.nextText();
                                String[] descriptionSectionArray = descriptionSection.split("; ");
                                String dateTimeWithLabel = descriptionSectionArray[0];
                                String[] dateTimeArray = dateTimeWithLabel.split(": ");
                                String dateTime = dateTimeArray[1];
                                String locationWithLabel = descriptionSectionArray[1];
                                String[] locationArray = locationWithLabel.split(": ");
                                String location = locationArray[1];
                                String latLongTogetherWithLabel = descriptionSectionArray[2];
                                String[] latLongTogetherArray = latLongTogetherWithLabel.split(": ");
                                String latLongTogether = latLongTogetherArray[1];
                                String[] latLongArray = latLongTogether.split(",");
                                float latitude = Float.parseFloat(latLongArray[0]);
                                float longitude = Float.parseFloat(latLongArray[1]);
                                String depthKmWithLabel = descriptionSectionArray[3];
                                String[] depthKmArray = depthKmWithLabel.split(": ");
                                String depthKm = depthKmArray[1];
                                String[] depthArray = depthKm.split(" ");
                                int depth = Integer.parseInt(depthArray[0]);
                                String magnitudeWithLabel = descriptionSectionArray[4];
                                String[] magnitudeArray = magnitudeWithLabel.split(": ");
                                float magnitude = Float.parseFloat(magnitudeArray[1]);

                                Earthquake earthquake = new Earthquake(location, dateTime, magnitude, depth, latitude, longitude);
                                earthquakes.add(earthquake);
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                        isItem = false;
                    }

                    eventType = xmlPullParser.next();
                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
        }

    }
}
