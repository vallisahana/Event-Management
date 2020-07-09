package com.example.myapplicationem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationem.Database.DatabaseUser;

import java.util.regex.Pattern;

public class UserRegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    EditText editTextname,editTextemail,editTextpassword,editTextphone;
    Button buttonuseregister;
    AppCompatCheckBox checkpass;

    TextView textViewuserlogin;

    DatabaseUser DH = new DatabaseUser(this);

    String Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        editTextname=findViewById(R.id.editname);
        editTextemail=findViewById(R.id.editemail);
        editTextpassword=findViewById(R.id.editpassword);
        checkpass=findViewById(R.id.checkregister);
        editTextphone=findViewById(R.id.editphone);
        buttonuseregister=findViewById(R.id.btnregister);
        textViewuserlogin=findViewById(R.id.textviewlogin);

        checkpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    editTextpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        textViewuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(UserRegisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });

        buttonuseregister.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                String fullname = editTextname.getText().toString().trim();
                Email = editTextemail.getText().toString().trim();
                String Pass = editTextpassword.getText().toString().trim();
                String Phone = editTextphone.getText().toString().trim();

                if(TextUtils.isEmpty(fullname)){
                    editTextname.setError( "Name is Required" );
                    editTextname.requestFocus() ;
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    editTextemail.setError( "Email is Required" );
                    editTextemail.requestFocus() ;
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(Email ).matches()){
                    editTextemail.setError( "please enter the vaild email" );
                    editTextemail.requestFocus(  );
                    return;
                }
                if(TextUtils.isEmpty(Pass)){
                    editTextpassword.setError( "password is Required" );
                    editTextpassword.requestFocus() ;
                    return;
                }

                if(!PASSWORD_PATTERN.matcher(Pass).matches()){
                    editTextpassword.setError( "please enter 1 uppercase,1 lowercase,1 digit,1 special character " );
                    editTextpassword.requestFocus(  );
                    return;
                }

                if(Pass.length()<6){
                    editTextpassword.setError( "minimum length of password should be 6" );
                    editTextpassword.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(Phone)){
                    editTextphone.setError( "Number is Required" );
                    editTextphone.requestFocus() ;
                    return;
                }

                if(!isValidPhone(editTextphone.getText().toString())){
                    editTextphone.setError( "length of phone number should be 10" );
                    editTextphone.requestFocus();
                    return;
                }
                boolean res = DH.User_Data(editTextname.getText().toString(),
                        editTextemail.getText().toString(),editTextpassword.getText().toString(),
                        editTextphone.getText().toString());
                MyMessage();
                if (res) {
                    Toast.makeText(UserRegisterActivity.this, "Registered Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(UserRegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserRegisterActivity.this, "Try Again",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void MyMessage() {
        String phonenumber=editTextphone.getText().toString();
       SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null, "Thank you for Registering to this Application, For Further Queries Contact to infoevent@gmail.com", null, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyMessage();
                } else {
                    Toast.makeText(this, "you dont have persmission", Toast.LENGTH_SHORT).show();
                }
        }

    }
    public boolean isValidPhone(String phone) {

        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length() <=9 || phone.length() > 10)
            {
                check = false;

            }
            else
            {
                check = true;

            }
        }
        else
        {
            check=false;
        }
        return check;
    }
}

