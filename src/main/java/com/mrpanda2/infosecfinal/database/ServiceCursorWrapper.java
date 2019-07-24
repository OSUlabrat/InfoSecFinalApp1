package com.mrpanda2.infosecfinal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

public class ServiceCursorWrapper extends CursorWrapper {
    public ServiceCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ServiceLogin getServiceLogin() {
        String serviceString = getString(getColumnIndex(ServiceDBSchema.Columns.SERVICE));
        String username = getString(getColumnIndex(ServiceDBSchema.Columns.USERNAME));

        ServiceLogin service = new ServiceLogin(serviceString);
        service.setUsername(username);

        return service;
    }
}
