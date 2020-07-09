package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationem.Database.DatabaseUser;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText pass,confirm;
    Button reset;

    DatabaseUser databaseUser;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        pass=findViewById(R.id.password);
        confirm=findViewById(R.id.confirm);
        reset=findViewById(R.id.reset);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        databaseUser = new DatabaseUser(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatePassword();
            }
        });
    }

    private void updatePassword() {

        String value1 = pass.getText().toString().trim();
        String value2 = confirm.getText().toString().trim();

        if (value1.isEmpty() && value2.isEmpty()){
            Toast.makeText(this, "fill all fields ", Toast.LENGTH_LONG).show();
            return;
        }

        if (!value1.contentEquals(value2)){
            Toast.makeText(this, "password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        if (!databaseUser.checkUser(email)) {

            Toast.makeText(this, "email doesn't exist", Toast.LENGTH_SHORT).show();
            return;

        } else {
            databaseUser.updatePassword(email, value1);

            Toast.makeText(this, "password reset successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void emptyInputEditText()
    {
        pass.setText("");
        confirm.setText("");
    }
}
