package com.example.myapplicationem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Databaseevent extends SQLiteOpenHelper {

    public static final String databasename="Eventdb";

    public static final String Event_table="Event_table";
    public static final int versioncode=1;

    public static final String Cust_name="Custname";
    public static final String Event_name="Eventname";
    public static final String Event_date="Eventdate";
    public static final String Select_event="Selectevent";
    public static final String Select_category="Selectcategory";
    public static final String Event_people="Eventpeople";
    public static final String Event_venue="Eventvenue";
    public static final String Event_phone="Eventphone";


    public Databaseevent(Context context){
        super(
                context,
                databasename,
                null,
                versioncode);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String event_query;
        event_query="CREATE TABLE IF NOT EXISTS "+Event_table+"(Custname TEXT,Eventname TEXT PRIMARY KEY,Eventdate TEXT ," +
                "Selectevent TEXT,Selectcategory TEXT,Eventpeople TEXT,Eventvenue TEXT,Eventphone TEXT)";
        database.execSQL(event_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String user_query;
        user_query = "DROP TABLE IF EXISTS " + Event_table;
        db.execSQL(user_query);
    }

    public boolean Event_Data(String cust,String eventname,String eventdate,String selectevent,String selectcategory,String eventpeople,
                              String eventvenue, String eventphone){

        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(Cust_name,cust);
        cv.put(Event_name,eventname);
        cv.put(Event_date,eventdate);
        cv.put(Select_event,selectevent);
        cv.put(Select_category,selectcategory);
        cv.put(Event_people,eventpeople);
        cv.put(Event_venue,eventvenue);
        cv.put(Event_phone,eventphone);

        long result=db1.insert(Event_table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor UserData()
    {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from "+Event_table,null);
        return res;
    }

    public Cursor searchClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("select * from "+Event_table+" Where Eventname=?",new String[]{name});
        return res;
    }

    public Cursor DeleteClient(String name) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res = db2.rawQuery("delete from "+Event_table+" Where Eventname=?",new String[]{name});
        return res;
    }


}
