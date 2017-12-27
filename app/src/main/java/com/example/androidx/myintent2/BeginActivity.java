package com.example.androidx.myintent2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BeginActivity extends AppCompatActivity {

    private static final String TAG = "InicioActivity";

    private EditText mNombre, mEmail, mTel, mMiscell, mLugar;
    //public static final String myString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        mNombre = (EditText) findViewById(R.id.eNombre);
        mEmail = (EditText) findViewById(R.id.eEmail);
        mTel = findViewById(R.id.eTel);
        mMiscell = findViewById(R.id.eHobbi);
        mLugar = findViewById(R.id.eLugar);
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


                Toast.makeText(this, " COMPLETE ", Toast.LENGTH_SHORT).show();

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





}
