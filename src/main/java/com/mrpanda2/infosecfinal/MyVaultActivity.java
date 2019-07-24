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

public class MyVaultActivity extends AppCompatActivity implements View.OnClickListener {
    Button searchButton;
    private EditText editText;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_vault);
        searchButton = (Button) findViewById(R.id.queryButton);
        tableLayout = (TableLayout) findViewById(R.id.maintable);
        editText = (EditText) findViewById(R.id.emailText);

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
