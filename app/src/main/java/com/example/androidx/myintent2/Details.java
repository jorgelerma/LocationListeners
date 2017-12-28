package com.example.androidx.myintent2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by androidx on 12/17/17.
 */

public class Details extends AppCompatActivity {


    private User myUser;

    private TextView selector;
    private String myStrings = "";
    private TextView result;
    private OkHttpClient client;
    private Button getBtn;
    JSONObject jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);

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


        if (getIntent().hasExtra("user")) {
            myUser = (User) getIntent().getSerializableExtra("user");
            Toast.makeText(this, myUser.toString(), Toast.LENGTH_LONG).show();


        }

        if (getIntent().hasExtra("name")) {
            Toast.makeText(this, getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
        }

        if (getIntent().hasExtra("tel")) {
            Toast.makeText(this, getIntent().getStringExtra("tel"), Toast.LENGTH_SHORT).show();
        }

        if (getIntent().hasExtra("miscell")) {
            Toast.makeText(this, getIntent().getStringExtra("miscell"), Toast.LENGTH_SHORT).show();
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


}