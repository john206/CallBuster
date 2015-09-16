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

public class ManageBlacklistActivity extends Activity {

    //global variables
	ArrayList<String> blockedNumbers;
	ArrayList<String> blockedAreaCodes;
    ArrayList<String> formattedNumbers;
    ArrayList<String> formattedAreaCodes;
	ListView listViewBlockedNumbers;
	ListView listViewBlockedAreaCodes;
	ArrayAdapter<String> blockedNumbersAdapter;
	ArrayAdapter<String> blockedAreaCodesAdapter;
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
		setContentView(R.layout.activity_manage_blacklist);		

        //initialize variables
		listViewBlockedNumbers = (ListView)findViewById(R.id.listViewBlockedNumbers);
		listViewBlockedAreaCodes = (ListView)findViewById(R.id.listViewBlockedAreaCodes);
		listUtility = (ListUtility)getApplicationContext();
		blockedNumbers = listUtility.getBlockedNumbers(new ArrayList<String>());
		blockedAreaCodes = listUtility.getBlockedAreaCodes(new ArrayList<String>());

        //populate the blocked numbers ListView
		if (!blockedNumbers.isEmpty()){

            //format the blocked number list for display
            formattedNumbers = listUtility.formatNumbers(blockedNumbers);

			blockedNumbersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formattedNumbers);
			listViewBlockedNumbers.setAdapter(blockedNumbersAdapter);
		}

        //populate the blocked area codes ListView
		if (!blockedAreaCodes.isEmpty()){

            //format the blocked area code list for display
            formattedAreaCodes = listUtility.formatAreaCodes(blockedAreaCodes);

			blockedAreaCodesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formattedAreaCodes);
			listViewBlockedAreaCodes.setAdapter(blockedAreaCodesAdapter);
		}
		
		//============================================================================
		//REMOVE NUMBER FROM BLACKLIST
		//============================================================================
		listViewBlockedNumbers.setOnItemClickListener(new OnItemClickListener() {
	            
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				
				//read the selected number
                Object listItem = listViewBlockedNumbers.getItemAtPosition(position);

                //parse the formatted number back into raw data
                String number = listUtility.parseNumberOrAreaCode(listItem);

                //delete the number
				deleteNumber(number);

			}
		});
		
		//============================================================================
		//REMOVE AREA CODE FROM BLACKLIST
		//============================================================================
		listViewBlockedAreaCodes.setOnItemClickListener(new OnItemClickListener() {
	            
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

                //read the selected area code
				Object listItem = listViewBlockedAreaCodes.getItemAtPosition(position);

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
	        	blockedNumbers.remove(number);
                listUtility.updateBlockedNumbers(blockedNumbers);
                
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
	        	blockedAreaCodes.remove(areaCode);
                listUtility.updateBlockedAreaCodes(blockedAreaCodes);
                
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
