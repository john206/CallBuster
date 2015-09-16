package net.irrelevant.callbuster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class WhitelistActivity extends Activity {

    EditText etNumber;
    EditText etAreaCode;
    ArrayList<String> allowedNumbers;
    ArrayList<String> allowedAreaCodes;
    ListUtility listUtility;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whitelist);



        listUtility = (ListUtility)getApplicationContext();
        allowedNumbers = listUtility.getAllowedNumbers(new ArrayList<String>());
        allowedAreaCodes = listUtility.getAllowedAreaCodes(new ArrayList<String>());

        etNumber = (EditText)findViewById(R.id.etWhitelistNumber);
        etAreaCode = (EditText)findViewById(R.id.etWhitelistAreaCode);

        //============================================================================
        //SHOW WARNING FOR EMPTY WHITELIST
        //============================================================================
        if (allowedNumbers.isEmpty() && allowedAreaCodes.isEmpty()){

            setContentView(R.layout.warning_whitelist_empty);
        }
    }

    //===============================================================================
    //ADD NUMBER TO ALLOWED NUMBERS LIST
    //===============================================================================
    public void addNumber(View view){

        String number = etNumber.getText().toString();

        if (validateNumber(number)) {

            allowedNumbers.add(number);
            listUtility.updateAllowedNumbers(allowedNumbers);
            finish();
            startActivity(getIntent());
        }
    }

    //===============================================================================
    //ADD AREA CODE TO ALLOWED AREA CODES LIST
    //===============================================================================
    public void addAreaCode(View view){

        String areaCode = etAreaCode.getText().toString();

        if (validateAreaCode(areaCode)) {

            allowedAreaCodes.add(areaCode);
            listUtility.updateAllowedAreaCodes(allowedAreaCodes);
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

        if (allowedNumbers.contains(number)){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etNumber.setText("");
            etNumber.setHintTextColor(Color.RED);
            etNumber.setHint("Number already allowed");

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

        if (allowedAreaCodes.contains(areaCode)){

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
            etAreaCode.setText("");
            etAreaCode.setHintTextColor(Color.RED);
            etAreaCode.setHint("Area code already allowed");

            return false;
        }

        return true;
    }

    //===============================================================================
    //START THE MANAGE WHITELIST ACTIVITY
    //===============================================================================
    public void startManageWhitelistActivity(View view){

        Intent intent = new Intent(this, ManageWhitelistActivity.class);
        startActivity(intent);
    }

    //============================================================================
    //PROCEEDS WITH ACTIVITY AFTER USER HAS ACKNOWLEDGED WARNING
    //============================================================================
    public void acknowledgeWarning(View view){

        setContentView(R.layout.activity_whitelist);
        etNumber = (EditText)findViewById(R.id.etWhitelistNumber);
        etAreaCode = (EditText)findViewById(R.id.etWhitelistAreaCode);
    }
}