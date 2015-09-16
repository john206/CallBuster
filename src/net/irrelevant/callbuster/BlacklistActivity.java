package net.irrelevant.callbuster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class BlacklistActivity extends Activity {

    EditText etNumber;
    EditText etAreaCode;
    ArrayList<String> blockedNumbers;
    ArrayList<String> blockedAreaCodes;
    ListUtility listUtility;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);

        listUtility = (ListUtility)getApplicationContext();
        blockedNumbers = listUtility.getBlockedNumbers(new ArrayList<String>());
        blockedAreaCodes = listUtility.getBlockedAreaCodes(new ArrayList<String>());

        etNumber = (EditText)findViewById(R.id.etBlacklistNumber);
        etAreaCode = (EditText)findViewById(R.id.etBlacklistAreaCode);
    }

    //===============================================================================
    //ADD NUMBER TO BLOCKED NUMBERS LIST
    //===============================================================================
    public void addNumber(View view){

        String number = etNumber.getText().toString();

        if (validateNumber(number)) {

            blockedNumbers.add(number);
            listUtility.updateBlockedNumbers(blockedNumbers);
            finish();
            startActivity(getIntent());
        }
    }

    //===============================================================================
    //ADD AREA CODE TO BLOCKED AREA CODES LIST
    //===============================================================================
    public void addAreaCode(View view){

        String areaCode = etAreaCode.getText().toString();

        if (validateAreaCode(areaCode)) {

            blockedAreaCodes.add(areaCode);
            listUtility.updateBlockedAreaCodes(blockedAreaCodes);
            finish();
            startActivity(getIntent());
        }
    }

    //===============================================================================
    //RUN SOME CHECKS ON THE NUMBER BEFORE ADDING IT
    //===============================================================================
    public boolean validateNumber(String number){

        if (number.length() != 10){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etNumber.setText("");
            etNumber.setHintTextColor(Color.RED);
            etNumber.setHint("Must be 10 digits");

            return false;
        }

        if (blockedNumbers.contains(number)){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etNumber.setText("");
            etNumber.setHintTextColor(Color.RED);
            etNumber.setHint("Number already blocked");

            return false;
        }

        return true;
    }

    //===============================================================================
    //RUN SOME CHECKS ON THE AREA CODE BEFORE ADDING IT
    //===============================================================================
    public boolean validateAreaCode(String areaCode){

        if (areaCode.length() != 3){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etAreaCode.setText("");
            etAreaCode.setHintTextColor(Color.RED);
            etAreaCode.setHint("Must be 3 digits");

            return false;
        }

        if (blockedAreaCodes.contains(areaCode)){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etAreaCode.setText("");
            etAreaCode.setHintTextColor(Color.RED);
            etAreaCode.setHint("Area code already blocked");

            return false;
        }

        return true;
    }

    //===============================================================================
    //START THE MANAGE BLACKLIST ACTIVITY
    //===============================================================================
    public void startManageBlacklistActivity(View view){

        startActivity(new Intent(this, ManageBlacklistActivity.class));
    }
}