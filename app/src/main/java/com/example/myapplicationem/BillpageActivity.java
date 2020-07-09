package com.example.myapplicationem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class BillpageActivity extends AppCompatActivity {

    TextView ename,bill;
    SharedPreferences pref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billpage);

        ename=findViewById(R.id.name);
        bill=findViewById(R.id.amount);

        pref = getApplicationContext().getSharedPreferences("spinnerevent", 0);
        pref = getApplication().getSharedPreferences("eventname",0);
        String amount = pref.getString("user_wieght", null);
        String name=pref.getString("eventname",null);
        ename.setText(name);
        bill.setText(amount);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Do you want to exit app?")
                .setNegativeButton("NO", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BillpageActivity.super.onBackPressed();
                    }
                }).create().show();



    }
}
