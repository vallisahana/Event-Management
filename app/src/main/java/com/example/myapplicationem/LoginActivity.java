package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationem.Database.DatabaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editTextemail,editTextpass;
    Button buttonuserlogin;
    TextView textView,forgot;
    AppCompatCheckBox checkBox;

    DatabaseUser databaseUser= new DatabaseUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkBox=findViewById(R.id.checklogin);
        editTextemail=findViewById( R.id.edituseremail );
        editTextpass=findViewById( R.id.edituserpassword);
        forgot=findViewById(R.id.forgot);

        buttonuserlogin=findViewById( R.id.btnlogin );
        textView=findViewById( R.id.textviewregister );

        textView.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserRegisterActivity.class );

                startActivity( intent );
            }
        } );

        forgot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class );

                startActivity( intent );
            }
        } );

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    editTextpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        buttonuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextemail.getText().toString();
                String Password = editTextpass.getText().toString();

                Boolean chkemailpass = databaseUser.emailpassword(email, Password);

                if (chkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_LONG).show();

                }


            }
        });




    }
}
