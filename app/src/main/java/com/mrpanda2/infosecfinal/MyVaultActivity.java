package com.mrpanda2.infosecfinal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mrpanda2.infosecfinal.database.Service;
import com.mrpanda2.infosecfinal.database.ServiceLogin;

import java.util.Calendar;
import java.util.List;

public class MyVaultActivity extends AppCompatActivity {
    Button userSearchButton;
    Button servSearchButton;
    Button updateButton;
    Button addButton;
    Button deleteButton;
    Button displayButton;

    Service myService;

    TextView text;

    private EditText userText;
    private EditText servText;
    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_vault);
        userSearchButton = (Button) findViewById(R.id.queryButton);
        servSearchButton = (Button) findViewById(R.id.serviceQueryButton);
        updateButton = (Button) findViewById(R.id.updateButton);
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        displayButton = (Button) findViewById(R.id.displayButton);

        text = (TextView) findViewById(R.id.statusText);

        myService = new Service(this);

        tableLayout = (TableLayout) findViewById(R.id.maintable);
        userText = (EditText) findViewById(R.id.emailText);
        servText = (EditText) findViewById(R.id.serviceText);

/*
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (tableLayout != null) {
                    tableLayout.removeAllViews();
                }
                getItem();

            }
        });
*/


    }

    //Add credential pair to db
    public void addCredentials(View view) {
        clearText(text);
        if(myService.addService(makeService(view)) > 0) {
            text.append("Service added!");
        } else {
            text.append("Service already has user. Did you mean update?");
        }
    }

    //If a service already has username, update it with new username and current date.
    public void updateService(View view) {
        ServiceLogin serv = makeService(view);
        serv.setDate(Calendar.getInstance().getTime());
        clearText(text);
        if(myService.updateService(serv) > 0)
        {
            text.append("Services updated");
        } else{
            text.append("No service found");
        }
    }

    //Delete service/username pair based on service name
    public void delateCredentials(View view){
        clearText(text);
        if(myService.deleteService(makeService(view)) > 0) {
            text.append("Service deleted");
        } else{
            text.append("No service found");
        }
    }

    //Display all services, with usernames and last update
    public void displayCredentials(View view) {
        List<ServiceLogin> allServices =  myService.getServices();
        clearText(text);
        for (ServiceLogin s : allServices) {
            text.append("Service: " + s.getService() + "\nUsername: " + s.getUsername() + "\nLast update: " + s.getDate() + "\n\n");
        }
    }

    //Find and display all services under username
    public void searchUsernames(View view) {
        List<ServiceLogin> allServices =  myService.getServices();
        clearText(text);
        for(ServiceLogin s : allServices) {
            if(s.getUsername().equals(userText.getText().toString())) {
                text.append("Service: " + s.getService() + "\nUsername: " + s.getUsername() + "\nLast update: " + s.getDate() + "\n\n");
            }
        }
    }

    //Find and display service login info
    public void searchService(View view) {
        ServiceLogin s = myService.getService(makeService(view).getService());
        clearText(text);
        if(s == null) {
            text.append("No service found");
        } else {
            text.setText("Service: " + s.getService() + "\nUsername: " + s.getUsername() + "\nLast update:" + s.getDate());
        }
    }

    //Make a service service/email pair
    private ServiceLogin makeService(View view) {
        servText = (EditText)findViewById(R.id.serviceText);
        userText = (EditText)findViewById(R.id.emailText);
        ServiceLogin serv = new ServiceLogin(servText.getText().toString().toUpperCase(), userText.getText().toString());
        return serv;
    }

    private void clearText(TextView text) {
        text.setText("");
    }
}

/*

    public void getItem() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String user = editText.getText().toString();
        String url = "https://haveibeenpwned.com/api/v2/breachedaccount/" + user + "?truncateResponse=true";
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.maintable);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String deleteChar = response.replaceAll("[\\[\\]{}:,\"]", "");
                        String[] split = deleteChar.split("Name");
                        for(int i =1;i<split.length;i++){
                            if(i % 2 == 0) {
                                TableRow row = new TableRow(MyVaultActivity.this);
                                TextView tv = new TextView(MyVaultActivity.this);
                                tv.setText(split[i]);
                                tv.setTextSize(25);
                                tv.setTextColor(0xff000000);
                                row.addView(tv);
                                row.setBackgroundColor(0xffff0000);
                                tableLayout.addView(row);
                            }
                            else{
                                TableRow row = new TableRow(MyVaultActivity.this);
                                TextView tv = new TextView(MyVaultActivity.this);
                                tv.setText(split[i]);
                                tv.setTextColor(0xff000000);
                                tv.setTextSize(25);
                                row.addView(tv);
                                row.setBackgroundColor(0xffff2C2C);
                                tableLayout.addView(row);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TableRow row = new TableRow(MyVaultActivity.this);
                TextView tv = new TextView(MyVaultActivity.this);
                tv.setText("Error");
                row.addView(tv);
                tableLayout.addView(row);
            }
        });

        queue.add(stringRequest);
    }
    @Override
    public void onClick(View view) {
        getItem();
    }
}
*/
