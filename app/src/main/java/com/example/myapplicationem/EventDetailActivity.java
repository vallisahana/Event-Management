package com.example.myapplicationem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationem.Adapter.Event_ListAdapter;
import com.example.myapplicationem.Adapter.User_ListAdapter;
import com.example.myapplicationem.Database.DatabaseUser;
import com.example.myapplicationem.Database.Databaseevent;
import com.example.myapplicationem.Model.Dataprovider_Event;
import com.example.myapplicationem.Model.Dataprovider_User;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    Databaseevent dbh;
    Cursor res;
    Event_ListAdapter la;
    Button button;

    EditText search;

    Button btndelete, btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        listView=findViewById(R.id.list_eventdetail);
        btnsearch = findViewById(R.id.search_teacher_btn);
        btndelete = findViewById(R.id.delete_teacher_btn);
        search = findViewById(R.id.search_user_name);


        dbh = new Databaseevent(getApplicationContext());
        sqLiteDatabase=dbh.getReadableDatabase();
        res=dbh.UserData();
        la=new Event_ListAdapter(getApplicationContext(),R.layout.user_row);
        listView.setAdapter(la);

        if(res.moveToFirst())
        {
            do{
                String cust,eventname,eventdate,selectevent,selectcategory,eventpeople,eventvenue,eventphone;

                cust=res.getString(0);
                eventname= res.getString(1);
                eventdate=res.getString(2);
                selectevent=res.getString(3);
                selectcategory=res.getString(4);
                eventpeople=res.getString(5);
                eventvenue=res.getString(6);
                eventphone=res.getString(7);


                Dataprovider_Event DPC= new Dataprovider_Event(cust,eventname,eventdate,selectevent,selectcategory,eventpeople,eventvenue,eventphone);
                la.add(DPC);
            }while(res.moveToNext());
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Dataprovider_Event dpc=(Dataprovider_Event) parent.getAdapter().getItem(position);

                dpc.getPhone();

                Intent intent=new Intent(EventDetailActivity.this,ApprovedActivity.class);
                intent.putExtra("bundles", dpc);
                startActivity(intent);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(search.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter the Event Name...", Toast.LENGTH_LONG).show();
                    return;
                }
                dbh = new Databaseevent(getApplicationContext());
                sqLiteDatabase = dbh.getReadableDatabase();
                Cursor res2 = dbh.DeleteClient(search.getText().toString());
                if (res2.getCount() > 0 && !res2.equals(res2)) {
                    Toast.makeText(EventDetailActivity.this, "Enter vaild Name", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(EventDetailActivity.this, "Successfully Deleted refresh to View", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
