package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    Button btnlogin;

    EditText adminemail,adminpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnlogin=findViewById(R.id.btnadminlogin);
        adminemail=findViewById(R.id.editemailadmin);
        adminpassword=findViewById(R.id.editpasswordadmin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminemail.getText().toString().equals("admin") && adminpassword.getText().toString().equals("admin")) {
                    Intent in = new Intent(AdminActivity.this, AdminHomectivity.class);
                    startActivity(in);
                    finish();
                } else {

                    Toast.makeText(AdminActivity.this, "Invalid Credentials..!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
