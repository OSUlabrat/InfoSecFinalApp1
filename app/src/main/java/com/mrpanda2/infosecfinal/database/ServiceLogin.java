package com.mrpanda2.infosecfinal.database;

import java.util.Calendar;
import java.util.Date;

public class ServiceLogin {
    private String username;
    private String service;
    private String date;

    public ServiceLogin() {

    }

    public ServiceLogin(String serv) {
        service = serv;
        username= "";
    }

    public ServiceLogin(String serv, String user) {
        username = user;
        service = serv;
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

    public void setDate (String d){
        date = d;
    }

    public String getDate() {
        return date;
    }
}
