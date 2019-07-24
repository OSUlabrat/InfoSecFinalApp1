package com.mrpanda2.infosecfinal;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;


public class checkPassActivity extends AppCompatActivity implements View.OnClickListener {
    Button checkButton;
    EditText editText;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_pass);
        editText = findViewById(R.id.originalPass);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);

        checkButton = (Button) findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                checkValues();
            }
        });
    }
    public void checkValues(){
        String password = editText.getText().toString();
        StringBuilder str = new StringBuilder();
        str.append(password);
        String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}";
        int count = 0;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasLength = false;
        for(int x = 0;x < str.length();x++){
            if(isLowerCase(str.charAt(x))){
                hasLower = true;
                count = count + 20;
            }
            else if(isUpperCase((str.charAt(x)))){
                hasUpper = true;
                count = count + 20;
            }
            else if(isDigit(str.charAt(x))){
                hasDigit = true;
                count = count + 20;
            }

            else if (specialCharacters.contains(Character.toString(str.charAt(x)))){
                hasSpecial = true;
                count = count + 20;
            }

        }
        if (str.length() >= 12){
            hasLength = true;
            count = count + 20;

        }
        if(hasUpper){
            img1.setImageResource(R.drawable.greencheck_round);
        }
        else{
            img1.setImageResource(R.drawable.redx_round);
        }
        if(hasLower){
            img2.setImageResource(R.drawable.greencheck_round);
        }
        else{
            img2.setImageResource(R.drawable.redx_round);
        }
        if(hasDigit){
            img3.setImageResource(R.drawable.greencheck_round);
        }
        else{
            img3.setImageResource(R.drawable.redx_round);
        }
        if(hasSpecial){
            img4.setImageResource(R.drawable.greencheck_round);
        }
        else{
            img4.setImageResource(R.drawable.redx_round);
        }
        if(hasLength){
            img5.setImageResource(R.drawable.greencheck_round);
        }
        else{
            img5.setImageResource(R.drawable.redx_round);
        }


    }
    @Override
    public void onClick(View view) {

    }
}