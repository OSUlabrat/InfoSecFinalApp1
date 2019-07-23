package com.mrpanda2.infosecfinal.database;

import java.util.Calendar;
import java.util.Date;

public class ServiceLogin {
    private String username;
    private String service;
    private Date date;

    public ServiceLogin() {

    }

    public ServiceLogin(String serv) {
        service = serv;
        username= "";
        date = Calendar.getInstance().getTime();
    }

    public ServiceLogin(String serv, String user) {
        username = user;
        service = serv;
        date = Calendar.getInstance().getTime();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        username = user;
    }

    public String getService() {
        return service;
    }

    public void setService(String serv) {
        service = serv;
    }

    public void setDate (Date d){
        date = d;
    }

    public Date getDate() {
        return date;
    }
}
