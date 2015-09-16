package net.irrelevant.callbuster;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.android.internal.telephony.ITelephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;


public class IncomingCallReceiver extends BroadcastReceiver {
	
	ITelephony telephonyService;
	
	ArrayList<String> blockedNumbers;
	ArrayList<String> blockedAreaCodes;

    ArrayList<String> allowedNumbers;
    ArrayList<String> allowedAreaCodes;

    ArrayList<String> log;
	
	ListUtility listUtility;
		
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//============================================================================
		//LISTEN TO THE PHONE FOR INCOMING CALLS
		//============================================================================
		TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		try{

            listUtility = (ListUtility)context.getApplicationContext();

            blockedNumbers = listUtility.getBlockedNumbers(new ArrayList<String>());
            blockedAreaCodes = listUtility.getBlockedAreaCodes(new ArrayList<String>());

            allowedNumbers = listUtility.getAllowedNumbers(new ArrayList<String>());
            allowedAreaCodes = listUtility.getAllowedAreaCodes(new ArrayList<String>());

            log = listUtility.getLog(new ArrayList<String>());

            DateFormat dateFormat = new SimpleDateFormat("MMM dd, y \t\t hh:mma");

            //============================================================================
            //REFLECT INTO THE HIDDEN TELEPHONY API
            //============================================================================
            Class c = Class.forName(tm.getClass().getName());
			Method m = c.getDeclaredMethod("getITelephony");
			m.setAccessible(true);
			telephonyService = (ITelephony)m.invoke(tm);
			Bundle bundle = intent.getExtras();
			String incoming = bundle.getString("incoming_number");
			Log.e("INCOMING", incoming);
			
			//============================================================================
			//ENDS THE CALL IF INCOMING AREA CODE OR NUMBER IS BLACKLISTED OR NOT WHITELISTED
			//============================================================================
            if ((incoming != null)
                && !allowedNumbers.isEmpty()){

                if (!isAllowedNumber(incoming)){

                    //block the call
                    telephonyService.endCall();

                    //log the block
                    Date date = new Date();
                    String formattedNumber = formatNumber(incoming);
                    String logString = formattedNumber + dateFormat.format((date));
                    if (!log.contains(logString)){

                        log.add(logString);
                        listUtility.updateLog(log);
                    }
                }
            }

            if ((incoming != null)
                && !allowedAreaCodes.isEmpty()){

                if (!isAllowedAreaCode(incoming)){

                    //block the call
                    telephonyService.endCall();

                    //log the block
                    Date date = new Date();
                    String formattedNumber = formatNumber(incoming);
                    String logString = formattedNumber + dateFormat.format((date));
                    if (!log.contains(logString)){

                        log.add(logString);
                        listUtility.updateLog(log);
                    }
                }
            }

            if ((incoming != null)
                && (isBlockedAreaCode(incoming) || isBlockedNumber(incoming))){

                //block the call
                telephonyService.endCall();

                //log the block
                Date date = new Date();
                String formattedNumber = formatNumber(incoming);
                String logString = formattedNumber + dateFormat.format((date));
                if (!log.contains(logString)){

                    log.add(logString);
                    listUtility.updateLog(log);
                }
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}		
	}

	//============================================================================
	//CHECKS BLACKLIST FOR INCOMING NUMBER
	//============================================================================
	private boolean isBlockedNumber(String incoming){
		
		if (blockedNumbers.contains(incoming)){
			
			return true;
		}
		
		return false;			
	}
	
	//============================================================================
	//CHECKS BLACKLIST FOR INCOMING AREA CODE
	//============================================================================
	private boolean isBlockedAreaCode(String incoming){
		
		for (String areaCode: blockedAreaCodes){
			
			if (incoming.substring(0, 3).equals(areaCode)){
				
				return true;
			}
		}
		
		return false;
	}

    //============================================================================
    //CHECKS WHITELIST FOR INCOMING NUMBER
    //============================================================================
    private boolean isAllowedNumber(String incoming){

        if (allowedNumbers.contains(incoming)){

            return true;
        }

        return false;
    }


    //============================================================================
    //CHECKS WHITELIST FOR INCOMING AREA CODE
    //============================================================================
    private boolean isAllowedAreaCode(String incoming){

        for (String areaCode: allowedAreaCodes){

            if (incoming.substring(0, 3).equals(areaCode)){

                return true;
            }
        }

        return false;
    }



    //============================================================================
    //FORMATS THE PHONE NUMBER FOR OUTPUT
    //============================================================================
    private String formatNumber(String incoming){

        String formattedNumber = "(" + incoming.substring(0,3) + ") "
                                     + incoming.substring(3,6) + "-"
                                     + incoming.substring(6,10);

        return formattedNumber;
    }



}


