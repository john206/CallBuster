package net.irrelevant.callbuster;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ManageWhitelistActivity extends Activity {

    //global variables
    ArrayList<String> allowedNumbers;
    ArrayList<String> allowedAreaCodes;
    ArrayList<String> formattedNumbers;
    ArrayList<String> formattedAreaCodes;
    ListView listViewAllowedNumbers;
    ListView listViewAllowedAreaCodes;
    ArrayAdapter<String> allowedNumbersAdapter;
    ArrayAdapter<String> allowedAreaCodesAdapter;
    ListUtility listUtility;

    //============================================================================
    //OVERRIDE BACK BUTTON TO SKIP TO HOME SCREEN
    //============================================================================
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_whitelist);

        //initialize variables
        listViewAllowedNumbers = (ListView)findViewById(R.id.listViewAllowedNumbers);
        listViewAllowedAreaCodes = (ListView)findViewById(R.id.listViewAllowedAreaCodes);
        listUtility = (ListUtility)getApplicationContext();
        allowedNumbers = listUtility.getAllowedNumbers(new ArrayList<String>());
        allowedAreaCodes = listUtility.getAllowedAreaCodes(new ArrayList<String>());

        //populate the allowed numbers ListView
        if (!allowedNumbers.isEmpty()){

            //format the allowed number list for display
            formattedNumbers = listUtility.formatNumbers(allowedNumbers);

            allowedNumbersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formattedNumbers);
            listViewAllowedNumbers.setAdapter(allowedNumbersAdapter);
        }

        //populate the allowed area code ListView
        if (!allowedAreaCodes.isEmpty()){

            //format the allowed area code list for display
            formattedAreaCodes = listUtility.formatAreaCodes(allowedAreaCodes);

            allowedAreaCodesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formattedAreaCodes);
            listViewAllowedAreaCodes.setAdapter(allowedAreaCodesAdapter);
        }

        //============================================================================
        //REMOVE NUMBER FROM WHITELIST
        //============================================================================
        listViewAllowedNumbers.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

                //read the selected number
                Object listItem = listViewAllowedNumbers.getItemAtPosition(position);

                //parse the formatted number back into raw data
                String number = listUtility.parseNumberOrAreaCode(listItem);

                //delete the number
                deleteNumber(number);

            }
        });

        //============================================================================
        //REMOVE AREA CODE FROM WHITELIST
        //============================================================================
        listViewAllowedAreaCodes.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

                //read the selected number
                Object listItem = listViewAllowedAreaCodes.getItemAtPosition(position);

                //parse the formatted area code back into raw data
                String areaCode = listUtility.parseNumberOrAreaCode(listItem);

                //delete the area code
                deleteAreaCode(areaCode);

            }
        });

    }



    //============================================================================
    //DELETES NUMBER VIA AN ALERT DIALOG
    //============================================================================
    public void deleteNumber(final String number){

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Delete this entry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        allowedNumbers.remove(number);
                        listUtility.updateAllowedNumbers(allowedNumbers);

                        //refresh the activity
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

    //============================================================================
    //DELETES AREA CODE VIA AN ALERT DIALOG
    //============================================================================
    public void deleteAreaCode(final String areaCode){

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Delete this entry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        allowedAreaCodes.remove(areaCode);
                        listUtility.updateAllowedAreaCodes(allowedAreaCodes);

                        //refresh the activity
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }



}

