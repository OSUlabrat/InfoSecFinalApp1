package com.mrpanda2.infosecfinal;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

import java.util.HashMap;
import java.util.Map;


public class webPullActivity extends AppCompatActivity implements View.OnClickListener {
    Button searchButton;
    private EditText editText;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_pull);
        searchButton =  findViewById(R.id.queryButton);
        tableLayout =  findViewById(R.id.maintable);
        editText =  findViewById(R.id.emailText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (tableLayout != null) {
                    tableLayout.removeAllViews();
                }
                getItem();

            }
        });



    }


    public void getItem() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String user = editText.getText().toString();
        String url = "https://haveibeenpwned.com/api/v3/breachedaccount/" + user;
        final TableLayout tableLayout =  findViewById(R.id.maintable);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String deleteChar = response.replaceAll("[\\[\\]{}:,\"]", "");
                        String[] split = deleteChar.split("Name");
                        for(int i =1;i<split.length;i++){
                            if(i % 2 == 0) {
                                TableRow row = new TableRow(webPullActivity.this);
                                TextView tv = new TextView(webPullActivity.this);
                                tv.setText(split[i]);
                                tv.setTextSize(25);
                                tv.setTextColor(0xffffffff );
                                row.addView(tv);
                                row.setBackgroundColor(0xffff0000);
                                tableLayout.addView(row);
                            }
                            else{
                                TableRow row = new TableRow(webPullActivity.this);
                                TextView tv = new TextView(webPullActivity.this);
                                tv.setText(split[i]);
                                tv.setTextColor(0xffffffff );
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
                TableRow row = new TableRow(webPullActivity.this);
                TextView tv = new TextView(webPullActivity.this);
                tv.setText("Error");
                row.addView(tv);
                tableLayout.addView(row);
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers ;
                headers = new HashMap<String, String>();
                headers.put("hibp-api-key", " 8e61a6c178664b34aea3e6437079c4aa");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        getItem();
    }
}
