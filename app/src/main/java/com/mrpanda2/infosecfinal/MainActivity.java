package com.mrpanda2.infosecfinal;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button searchButton;
    Button generateButton;
    Button checkButton;
    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton =  findViewById(R.id.queryButton);
        generateButton =  findViewById(R.id.generateButton);
        checkButton =  findViewById(R.id.checkButton);
        img1 = findViewById(R.id.img1);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, webPullActivity.class);
                startActivity(intent);

            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, checkPassActivity.class);
                startActivity(intent);

            }
        });

       generateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, passGenActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onClick(View view) {

    }
}

