package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationem.Database.DatabaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;
    Button confirm;

    DatabaseUser databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email=findViewById(R.id.textInputEditTextEmail);
        confirm=findViewById(R.id.appCompatButtonConfirm);

        databaseUser = new DatabaseUser(this);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyFromSQLite();
            }
        });
    }

    private void verifyFromSQLite(){

        if (email.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (databaseUser.checkUser(email.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, ResetPasswordActivity.class);
            accountsIntent.putExtra("EMAIL", email.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(this, "Invaild Email", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText(){
        email.setText("");
    }
}
