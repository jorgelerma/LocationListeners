package com.example.androidx.myintent2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by androidx on 12/17/17.
 */

public class Details extends AppCompatActivity implements LocationListener {


    private User myUser;

    private TextView selector;
    private String myStrings = "";
    private TextView result;
    private OkHttpClient client;
    private Button getBtn;
    JSONObject jsonResponse;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);


        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        selector = findViewById(R.id.dSelecting);
        result = (TextView) findViewById(R.id.dresultz);
        getBtn = (Button) findViewById(R.id.dhttpz);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicez();
            }
        });

        client = new OkHttpClient();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,   // 3 sec
                10, this);




        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                1000,   // 3 sec
                10, this);


        double latitude = 0;
        double longitude = 0;

        if (locationManager != null) {
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.d("LOCAL", "****** " + latitude);
                Log.d("LOCAL", "****** " + longitude);

                result.setText("Lat: " + latitude + " \n LONG: " + longitude);
            }
        }

        //locationManager.requestLocationUpdates(getProviderName(), minTime,
                //minDistance, locationListener);


        if (getIntent().hasExtra("user")) {
            myUser = (User) getIntent().getSerializableExtra("user");
            // TO REMOVE COMMENTED OUT
            //Toast.makeText(this, myUser.toString(), Toast.LENGTH_LONG).show();


        }

        if (getIntent().hasExtra("name")) {
            //Toast.makeText(this, getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
        }

        if (getIntent().hasExtra("tel")) {
            //Toast.makeText(this, getIntent().getStringExtra("tel"), Toast.LENGTH_SHORT).show();
        }

        if (getIntent().hasExtra("miscell")) {
            //Toast.makeText(this, getIntent().getStringExtra("miscell"), Toast.LENGTH_SHORT).show();
            //setSelectors(getIntent().getStringExtra("miscell"));
            myStrings = getIntent().getStringExtra("miscell");

        }

        setSelectors(myStrings);


    }


    public void calling(View view) {

        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:" + myUser.getTel()));
        startActivity(i);
    }

    // sets the extra TEXTVIEW
    public void setSelectors(String textz) {

        selector.setText(textz);
    }


    /*
    // to set HTTP request
    public void requesterz(){

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }

    }
    */

    // ADDED FOR OKHTTP

    public void servicez() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {


                final Request request = new Request.Builder()
                        .url("http://quotes.stormconsultancy.co.uk/random.json")
                        .build();


                //final Request request = new Request.Builder().url("http://www.ssaurel.com/tmp/todos").build();
                client.newCall(request).enqueue(new Callback() {  // client
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText("Failure !");
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {

                        // COMMENTED OUT THE runOnUiThread
                        //runOnUiThread(new Runnable() {

                        // COMMENTED OUT THE runOnUiThread the Run
                    /*
                    @Override
                    public void run() {
                    */

                        // COMMENTED OUT THE runOnUiThread  the try
                        /*
                        try {
                        */
                        //result.setText(response.body().string());
                        //result.setText(response.body().string();
                        //response.
                        //JSONObject jsonResponse;
                        JSONObject jsonResponse = new JSONObject();


                        try {
                            jsonResponse = new JSONObject(response.body().string());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //String myVar1 = (String) jsonResponse.get("quote");

                        String myVar1 = null;

                        try {
                            myVar1 = jsonResponse.getString("quote");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                         String myVar2 = null;
                        try {
                            myVar2 = jsonResponse.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //result.setText(myVar1);

                        final String myVar2z = myVar2;
                        final String myVar1z = myVar1;

                        //  ADDING THE RUN ON UITHREAD
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //result.setText(response.body().string());
                                result.setText("IDS: " + myVar2z + "\n " + " Quote of the day: " + myVar1z);

                                Log.d("DONE", "Request completed set text to TEXTVIEWS");
                            }
                        });

                        // COMMENTED IT OUT AFTER,  ADDING THE RUN ON UITHREAD
                        //result.setText("IDS: " + myVar2 + "\n " + " Quote of the day: " + myVar1);


                        // COMMENTD AFTER THE ABOVE TRY AND CATCH
                /*
                            catch (IOException ioe){
                    result.setText("Error during get body");
                }
                */


                        // COMMENTED OUT THE runOnUiThread  the try
                            /*
                        } catch (IOException ioe) {
                            result.setText("Error during get body");
                          */

                        // COMMENTED OUT THE runOnUiThread the Run
                        /*
                        }
                        */

                        //  // COMMENTD AFTER THE ABOVE TRY AND CATCH
                        /*
                        catch (JSONException e) {
                            result.setText("JSON Exception");
                            e.printStackTrace();
                        }
                        */


                        // COMMENTED OUT THE runOnUiThread
                        //}
                        //});
                    }

                }); // client


                // ADDED CLOSING RUN METHOD

            }


            // ADDED FOR NEW THREAD
            //});

            // ADDED TO START NEW THREAD
            //thread.start();
            //}


        });

        thread.start();

    } // THIS IS SERVICES


    // THIS IS FOR GPS LOCATION
    public void locationGPS(View view) {

        boolean isNetworkEnabled;
        boolean isGPSEnabled;

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        // getting network status
        //isNetworkEnabled = locationManager
                //.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);


        // THIS IS GPS ENABLE CLOSING
        /*
        if (isGPSEnabled) {
            if (location == null) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                Log.d("GPS Enabled", "GPS Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
        }
        */
        // THIS IS GPS ENABLE CLOSING


        /*
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.d("GPS", "GPS enable: ");
        }
        */







    }  // THIS IS FOR GPS LOCATION, CLOSSING


    private int counterz = 0;

    // THIS IS FOR ADDED LOCATION LISTENER OPENING
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            double longitudeNetwork = location.getLongitude();
            double latitudeNetwork = location.getLatitude();

            String tmpString = String.valueOf(longitudeNetwork);

            Toast.makeText(getBaseContext(), tmpString, Toast.LENGTH_LONG).show();

            Log.d("LONGITUD", " THIS IS LONGITUD: " + tmpString);


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {



            //result.setText("Lat: " + latitude + " \n LONG: " + longitude);

        }

        @Override
        public void onProviderEnabled(String s) {


            Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onProviderDisabled(String s) {

            Toast.makeText(getBaseContext(), "Gps turned OFF ", Toast.LENGTH_LONG).show();

        }


    };  // THIS IS FOR ADDED LOCATION LISTENER CLOSING

    @Override
    public void onLocationChanged(Location location) {

        double longitudeNetwork = location.getLongitude();
        double latitudeNetwork = location.getLatitude();
        double altitudGPS = location.getAltitude();
        double speedGPS = location.getSpeed();
        double timeGPS = location.getTime();
        double bearingGPS = location.getBearing();
        String providerGPS = location.getProvider();

        String longitudeString = String.valueOf(longitudeNetwork);
        String latitudeString = String.valueOf(latitudeNetwork);
        String altitudString = String.valueOf(altitudGPS);
        String speedString = String.valueOf(speedGPS);
        String timeString = String.valueOf(timeGPS);
        String bearingString = String.valueOf(bearingGPS);

        Toast.makeText(getBaseContext(), longitudeString, Toast.LENGTH_LONG).show();

        Log.d("LONGITUD", " THIS IS LONGITUD: " + longitudeString);

        int numCount = counters(counterz);

        counterz++;

        //Date date = new Date(1318386508000L);
        Date date = new Date(((long) timeGPS));
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        format.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        String formatted = format.format(date);

        result.setText(" ON LOCATION CHANGE: " + "\n  Counters: " + counterz + " \n  LATITUDE: " + latitudeString + " \n LONGITUDE: " + longitudeString
        + " \n ALTITUD: "  + altitudString + " \n SPEED: " + speedString + " \n Time: " + timeString
        + " \n BEARING: " + bearingString + " \n PROVIDER: " + providerGPS + " \n CURRENT TIME: " + formatted);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

        Toast.makeText(getBaseContext(), "Gps turned ON ", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String s) {

        Toast.makeText(getBaseContext(), "Gps turned OFF ", Toast.LENGTH_LONG).show();

    }


    public int counters(int vars){

        return vars++;
    }


}