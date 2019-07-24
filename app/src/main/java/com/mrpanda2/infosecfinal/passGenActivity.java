package com.mrpanda2.infosecfinal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;


public class passGenActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText original;
    private EditText strongerPassword;
    String[] spec = {"!","@","#","$","%","^","&","*"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_gen);
        original =  findViewById(R.id.originalPass);
        strongerPassword =  findViewById(R.id.strongPass);
        Button generateButton =  findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String original = getOriginal();
                String newPassword = strPass(original);
                strongerPassword.setText(newPassword);

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
    //Main password strengthener that calls all other functions
    public String strPass(String password){

        int[] countAll = getCount(password);
        String fixString =  fixLength(password);
        String strongString = getStronger(fixString,countAll);
        String baseString = baseTransform(strongString);

        return baseString;
    }

    //Gets the value from the edittext from user
    public String getOriginal(){
        return original.getText().toString();
    }
    public String getStronger(String password, int[] counts){
        String newString = "";
        int countU = counts[0];

        int countN = counts[2];
        StringBuilder str = new StringBuilder();
        str.append(password);
        Random rand = new Random();
        while (countU < 3 || countN < 3) {
            if (countU < 3){
            for (int i = 0; i < str.length(); i++) {
                if(isLowerCase(str.charAt(i)) && isLetter(str.charAt(i))){
                    str.deleteCharAt(i);
                    str.insert(i, toUpperCase(str.charAt(i)));
                    countU++;
                }
                if (countU > 2){
                    break;
                }
            }
            }
            if (countN < 3){
                int chance = rand.nextInt(10);
                int i = rand.nextInt(9);
                if(chance < 5){
                    str.insert(0, i);
                    countN++;
                }
                else{
                    str.insert(str.length(), i);
                    countN++;
                }
            }

        }
        return str.toString();
    }
    //Counts uppercase, lowercase and digits
    public int[] getCount(String password){

        int countU = 0;
        int countL = 0;
        int countN = 0;

        StringBuilder str = new StringBuilder();
        str.append(password);
        for(int x =0; x < str.length();x++){
            if(isLowerCase(str.charAt(x))){
                countL++;
            }
            else if(isUpperCase(str.charAt(x))){
                countU++;
            }
            else if(isDigit(str.charAt(x))){
                countN++;
            }
        }
        int[] newArray = {countU, countL, countN};
        return newArray;
    }
    //Makes sure the length is 10
    public String fixLength(String password){
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        String newString = password;
        StringBuilder sbPassword = new StringBuilder();
        sbPassword.append(password);

        if (password.length() < 12){
            int diff = 10 - password.length();
            for(int x =0;x<diff;x++){
                int r1 = rand.nextInt(3);
                if(r1 == 1){
                    sbPassword.insert(sbPassword.length(),alphabet.charAt(rand.nextInt(36)));
                }
                else if(r1 == 2){
                    sbPassword.insert(rand.nextInt(sbPassword.length()),alphabet.charAt(rand.nextInt(36)));
                }
                else{
                    sbPassword.insert(0,alphabet.charAt(rand.nextInt(36)));
                }

            }


        }

        newString = sbPassword.toString();
        return newString;

    }

    //Adds special characters randomly into password
    public String baseTransform(String password){
        Random rand = new Random();
        String begin = spec[rand.nextInt(8)];
        String end = spec[rand.nextInt(8)];
        StringBuilder str = new StringBuilder();
        str.append(password);
        str.insert(rand.nextInt(password.length()), begin);
        str.insert(rand.nextInt(password.length()), end);


        return str.toString();

    }
}
