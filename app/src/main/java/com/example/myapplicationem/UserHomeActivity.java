package com.example.myapplicationem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationem.Database.DatabaseUser;
import com.example.myapplicationem.Database.Databaseevent;

import java.util.Calendar;

public class UserHomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    EditText cust,eventname,eventpeople,eventvenue,eventphone;
    Spinner spinnerevent,spinnercountry;
    Button buttonsubmit;
    TextView eventdate,bill;

    SharedPreferences pref ;


    Databaseevent DH = new Databaseevent(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        pref = getApplicationContext().getSharedPreferences("spinnerevent", 0);
        pref = getApplication().getSharedPreferences("eventname",0);

        cust=findViewById(R.id.editcustname);
        eventname=findViewById(R.id.editeventname);
        eventdate=findViewById(R.id.editeventdate);
        eventpeople=findViewById(R.id.editeventpeople);
        eventvenue=findViewById(R.id.editeventvenue);
        spinnerevent=findViewById(R.id.spinnerevent);
        spinnercountry=findViewById(R.id.spinnercountry);
        eventphone=findViewById(R.id.editeventphone);
        buttonsubmit=findViewById(R.id.btnsubmit);

        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.events,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerevent.setAdapter(adapter);
        spinnerevent.setOnItemSelectedListener(this);

        final ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercountry.setAdapter(adapter1);
        spinnercountry.setOnItemSelectedListener(this);




        eventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int d, m, y;
                Calendar calendar = Calendar.getInstance();
                d = calendar.get(Calendar.DAY_OF_MONTH);
                m = calendar.get(Calendar.MONTH);
                y = calendar.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(
                        UserHomeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                                mm += 1;
                                eventdate.setText(dd +"/"+ mm +"/"+ yyyy );
                            }
                        },
                        y, m, d);
                pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                pickerDialog.show();
            }
        });


        buttonsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (TextUtils.isEmpty(eventname.getText().toString()) ||
                            TextUtils.isEmpty(eventdate.getText().toString()) ||
                            TextUtils.isEmpty(eventpeople.getText().toString())||
                            TextUtils.isEmpty(eventphone.getText().toString())||
                            TextUtils.isEmpty(spinnerevent.getSelectedItem().toString())||
                            TextUtils.isEmpty(spinnercountry.getSelectedItem().toString())||
                            TextUtils.isEmpty(eventvenue.getText().toString())){

                        Toast.makeText(UserHomeActivity.this, "Pls fill the fields",
                                Toast.LENGTH_LONG).show();



                    } else {
                        int mobile = eventphone.length();
                          String name = eventname.getText().toString();
                         //String email = editTextemail.getText().toString();
                        // String password=editTextphone.getText().toString();

                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("eventname",name);
                        editor.commit();


                        if (mobile > 10 || mobile < 10) {
                            Toast.makeText(UserHomeActivity.this, "Invalid Mobile Number",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            boolean res = DH.Event_Data(cust.getText().toString(),eventname.getText().toString(),eventdate.getText().toString(),
                                    spinnerevent.getSelectedItem().toString(),spinnercountry.getSelectedItem().toString(),
                                    eventpeople.getText().toString(),eventvenue.getText().toString(),
                                    eventphone.getText().toString());
                            if (res) {

                                Toast.makeText(UserHomeActivity.this, "Submitted Successfully",
                                        Toast.LENGTH_LONG).show();

                                Intent intent=new Intent(UserHomeActivity.this,BillpageActivity.class);
                                startActivity(intent);
                                finish();

                            }

                            eventname.setText("");
                            eventphone.setText("");
                            eventvenue.setText("");
                            eventdate.setText("");
                            eventpeople.setText("");
                        }
                    }
                }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Object item = parent.getItemAtPosition(position);
        if (position == 1) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "50,0000");
            editor.commit();
        } else if (position == 2) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "10,0000");
            editor.commit();
        } else if (position == 3) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "5,000");
            editor.commit();
        } else if (position == 4) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "20,0000");
            editor.commit();
        } else if (position == 5) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "30,0000");
            editor.commit();

        }else if (position == 6) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_wieght", "60,0000");
            editor.commit();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
