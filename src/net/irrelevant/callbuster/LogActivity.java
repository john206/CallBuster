package net.irrelevant.callbuster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogActivity extends Activity {

    ArrayList<String> log;
    ListView listViewLog;
    //ArrayAdapter<String> logAdapter;
    ListUtility listUtility;

    //============================================================================
    //OVERRIDE BACK BUTTON TO SKIP TO RESTART THE MAIN ACTIVITY
    //============================================================================
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        listViewLog = (ListView)findViewById(R.id.listViewLog);
        listUtility = (ListUtility)getApplicationContext();

        log = listUtility.getLog(new ArrayList<String>());

        //UNDER CONSTRUCTION
        List<Map<String,String>> data = new ArrayList<Map<String, String>>();
        for (String item: log){

            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("number", item.substring(0,14));
            datum.put("time", item.substring(14));
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter
                (this, data, android.R.layout.simple_list_item_2,
                    new String[]{"number", "time"},
                    new int[]{android.R.id.text1, android.R.id.text2});

        listViewLog.setAdapter(adapter);


//        if (!log.isEmpty()){
//
//            logAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, log);
//            listViewLog.setAdapter(logAdapter);
//        }
    }


    //=================================================================================
    //CLEARS THE LOG
    //=================================================================================
    public void clearLog(View view){

        if (!log.isEmpty()){

            new AlertDialog.Builder(this)
                    .setTitle("Clear log")
                    .setMessage("Clear the log?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //proceed
                            log.clear();
                            listUtility.updateLog(log);
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
}