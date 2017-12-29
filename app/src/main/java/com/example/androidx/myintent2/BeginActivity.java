package com.example.androidx.myintent2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BeginActivity extends AppCompatActivity {

    TextView txtString;
    public String url= "http://jsonplaceholder.typicode.com/posts/1";

    private static final String TAG = "InicioActivity";

    private EditText mNombre, mEmail, mTel, mMiscell, mLugar;
    //public static final String myString = "";
    private Button mybutton2;

    private TextView netStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        txtString = findViewById(R.id.eTexting2);

        mNombre = (EditText) findViewById(R.id.eNombre);
        mEmail = (EditText) findViewById(R.id.eEmail);
        mTel = findViewById(R.id.eTel);
        mMiscell = findViewById(R.id.eHobbi);
        mLugar = findViewById(R.id.eLugar);
        mybutton2 = findViewById(R.id.eButton2);
        netStatus = findViewById(R.id.eNetStatus);

        mybutton2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                requestings();
            }
        });


    }

        // myString = String.valueOf(mMiscell.getText());

        public void actionz(View view){

        /*
            if(mNombre.getText().toString().isEmpty()){

                mNombre.setError(" Enter your name! ");
                mNombre.requestFocus();
            }else if(mEmail.getText().toString().isEmpty()){

                mEmail.setError(" Enter your email! ");
                mEmail.requestFocus();
                mEmail.setText("Enter your fricken emal ");
            }else if(mTel.getText().toString().isEmpty()){

                mTel.setError(" Enter your number: ");
                mTel.requestFocus();
            }

            else if(mMiscell.getText().toString().isEmpty()){
                mMiscell.setHint("SET YOUR ADDRESS: ");
                //mMiscell.requestFocus();
            }else{

        */
                User newuser = new User();

                newuser.setName(mNombre.getText().toString());
                newuser.setEmail(mEmail.getText().toString());
                newuser.setTel((mTel.getText().toString()));
                newuser.setMiscell(mMiscell.getText().toString());

                // TO REMOVE COMMENTED OUT
                //Toast.makeText(this, " COMPLETE ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this,Details.class);

                //Intent i = new Intent(this,DetalleActivity.class);


                Bundle bun = new Bundle();
                bun.putSerializable("user",newuser);
                i.putExtras(bun);

                i.putExtra("name", newuser.getName());
                i.putExtra("email", newuser.getEmail());
                i.putExtra("tel", newuser.getTel());
                i.putExtra("miscell", newuser.getMiscell());
                i.putExtra("myValue","This is my ValueL");


                startActivity(i);







                //Log.d("DONE", mMiscell.getText().toString());


                /*
                Intent x = new Intent(this, Details.class);

                //Serialization
                Bundle buns = new Bundle();
                buns.putSerializable("user", newuser);
                x.putExtras(buns);

                startActivity(x);
                */

                //Log.i(TAG, newuser.toString());





            //}


    }


    // FOR HTTP REQUEST USING OKHTTP
    public void requestings(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();



        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        txtString.setText(" BIG TIME FAILURE: ");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

               //final String myString = response.body().string();
                String myString = null;
                String myVar = null;

                JSONObject myJSONObject = null;

                try {

                    myVar = response.networkResponse().toString();
                    Log.d("NETWORKZ", "This is Network Response: " + myVar);

                    myJSONObject = new JSONObject(response.body().string());
                     myString = myJSONObject.getString("body");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final String myStringz = myString;
                final String myVarz = myVar;


                        runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        txtString.setText(myStringz);

                        netStatus.setText(myVarz);
                        Log.i("DONER", "Setting the text");
                    }
                });

            }
        });




    }





}
