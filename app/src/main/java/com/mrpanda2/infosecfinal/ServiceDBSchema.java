package com.mrpanda2.infosecfinal.database;

public class ServiceDBSchema {
    public final static class ServiceTable{
        public static final String NAME = "serviceLogins";
    }

    //String references
    public static final class Columns {
        public static final String SERVICE = "service";
        public static final String USERNAME = "username";
        public static final String DATE = "date";
    }
}