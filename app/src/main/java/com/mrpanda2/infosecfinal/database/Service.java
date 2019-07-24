package com.mrpanda2.infosecfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mrpanda2.infosecfinal.database.ServiceBaseHelper;
import com.mrpanda2.infosecfinal.database.ServiceCursorWrapper;
import com.mrpanda2.infosecfinal.database.ServiceDBSchema;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public Service(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ServiceBaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValues(ServiceLogin serviceLogin) {
        ContentValues values = new ContentValues();
        values.put(ServiceDBSchema.Columns.SERVICE, serviceLogin.getService());
        values.put(ServiceDBSchema.Columns.USERNAME, serviceLogin.getUsername());
        values.put(ServiceDBSchema.Columns.DATE, serviceLogin.getDate());

        return values;
    }

    //Query db for entry using name of service
    public ServiceLogin getService(String service) {
        ServiceCursorWrapper cursor = queryServices(ServiceDBSchema.Columns.SERVICE + " = ?", new String[] {service});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getServiceLogin();
        } finally {
            cursor.close();
        }
    }

    //Query db for list of all service logins
    public List<ServiceLogin> getServices() {
        List<ServiceLogin> services = new ArrayList<>();

        ServiceCursorWrapper cursor = queryServices(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                services.add(cursor.getServiceLogin());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return services;
    }

    //Update an existing service with new username. Returns number of rows affected (i.e. 0 if no update)
    public int updateService(ServiceLogin service) {
        String nameString = service.getService();
        ContentValues values = getContentValues(service);

        return mDatabase.update(ServiceDBSchema.ServiceTable.NAME, values, ServiceDBSchema.Columns.SERVICE + " = ?", new String[] {nameString});
    }

    private ServiceCursorWrapper queryServices(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(ServiceDBSchema.ServiceTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new ServiceCursorWrapper(cursor);
    }

    //Adds new service login. Does not replace existing existing pair. Return -1 if error, otherwise row ID.
    public long addService(ServiceLogin s) {
        ContentValues values = getContentValues(s);

        return mDatabase.insert(ServiceDBSchema.ServiceTable.NAME, null, values);
    }

    //Deletes pair tied to service. Return rows affected
    public int deleteService(ServiceLogin s) {
        return mDatabase.delete(ServiceDBSchema.ServiceTable.NAME, ServiceDBSchema.Columns.SERVICE + " = ?", new String[]{s.getService()});
    }
}
