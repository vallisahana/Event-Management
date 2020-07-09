package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomectivity extends AppCompatActivity {

    Button buttonuser,buttonevent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homectivity);

        buttonuser=findViewById(R.id.btnuser);
        buttonevent=findViewById(R.id.btnevent);

        buttonuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(AdminHomectivity.this, UserDetailActivity.class);
                startActivity(in);
            }
        });

        buttonevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminHomectivity.this, EventDetailActivity.class);
                startActivity(in);

            }
        });





    }


    public void logout(View view){
        Intent intent=new Intent(AdminHomectivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
