package com.example.myapplicationem.Model;

// class with getter and setter methods to create single userdata as an object.

import java.io.Serializable;

public class Dataprovider_Event implements Serializable {


    private String  cust,eventname, eventdate, selectevent, selectdish,eventpeople,eventvenue,  phone;

    public Dataprovider_Event() {
    }

    public Dataprovider_Event(String cust, String eventname, String eventdate, String selectevent, String selectdish, String eventpeople, String eventvenue, String phone) {
        this.cust = cust;
        this.eventname = eventname;
        this.eventdate = eventdate;
        this.selectevent = selectevent;
        this.selectdish = selectdish;
        this.eventpeople = eventpeople;
        this.eventvenue = eventvenue;
        this.phone = phone;
    }


    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getSelectevent() {
        return selectevent;
    }

    public void setSelectevent(String selectevent) {
        this.selectevent = selectevent;
    }

    public String getSelectdish() {
        return selectdish;
    }

    public void setSelectdish(String selectdish) {
        this.selectdish = selectdish;
    }

    public String getEventpeople() {
        return eventpeople;
    }

    public void setEventpeople(String eventpeople) {
        this.eventpeople = eventpeople;
    }

    public String getEventvenue() {
        return eventvenue;
    }

    public void setEventvenue(String eventvenue) {
        this.eventvenue = eventvenue;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
