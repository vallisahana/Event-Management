package com.example.myapplicationem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplicationem.ApprovedActivity;
import com.example.myapplicationem.EventDetailActivity;
import com.example.myapplicationem.Model.Dataprovider_Event;
import com.example.myapplicationem.Model.Dataprovider_User;
import com.example.myapplicationem.R;

import java.util.ArrayList;
import java.util.List;

public class Event_ListAdapter extends ArrayAdapter {


    List list = new ArrayList();

    public Event_ListAdapter
            (Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView cust,txteventname, txteventdate, txtselectevent, txtselectcategory, txtpeople, txteventvenue, txtphone;
        Button button;
    }

    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Event_ListAdapter.LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.event_row, parent, false);
            layoutHandler = new Event_ListAdapter.LayoutHandler();
            layoutHandler.cust = row.findViewById(R.id.txtcustname);
            layoutHandler.txteventname = row.findViewById(R.id.txteventname);
            layoutHandler.txteventdate = row.findViewById(R.id.txteventdate);
            layoutHandler.txtselectevent = row.findViewById(R.id.txtselectevent);
            layoutHandler.txtselectcategory = row.findViewById(R.id.txtcategory);
            layoutHandler.txtpeople = row.findViewById(R.id.txtnofpeople);
            layoutHandler.txteventvenue = row.findViewById(R.id.txteventvenue);
            layoutHandler.txtphone = row.findViewById(R.id.txtphone);
           // layoutHandler.button=row.findViewById(R.id.button);



            row.setTag(layoutHandler);
        } else {
            layoutHandler = (Event_ListAdapter.LayoutHandler) row.getTag();


        }

        Dataprovider_Event DPC = (Dataprovider_Event) this.getItem(position);
        layoutHandler.cust.setText(DPC.getCust());
        layoutHandler.txteventname.setText(DPC.getEventname());
        layoutHandler.txteventdate.setText(DPC.getEventdate());
        layoutHandler.txtselectevent.setText(DPC.getSelectevent());
        layoutHandler.txtselectcategory.setText(DPC.getSelectdish());
        layoutHandler.txtpeople.setText(DPC.getEventpeople());
        layoutHandler.txteventvenue.setText(DPC.getEventvenue());
        layoutHandler.txtphone.setText(DPC.getPhone());


        return row;
    }

}













































