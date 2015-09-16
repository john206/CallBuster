package net.irrelevant.callbuster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListUtility listUtility;
    ArrayList<String> log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        listUtility = (ListUtility)getApplicationContext();
        log = listUtility.getLog(new ArrayList<String>());
	}


	
	//==============================================================================
	//Activities 
	//==============================================================================
	

    public void startBlacklistActivity(View view){

        startActivity(new Intent(this, BlacklistActivity.class));
    }

    public void startWhitelistActivity(View view){

        startActivity(new Intent(this, WhitelistActivity.class));
    }
	
	public void startLogActivity(View view){

        startActivity(new Intent(this, LogActivity.class));
    }

}
