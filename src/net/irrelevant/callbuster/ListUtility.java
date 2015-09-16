package net.irrelevant.callbuster;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Application;
import android.widget.Toast;

//=======================================================================
//UTILITY CLASS TO MANAGE INTERNAL STORAGE
//=======================================================================
public class ListUtility extends Application {
	


	//=======================================================================
	//RETURNS LIST OF BLOCKED NUMBERS
	//=======================================================================
	public ArrayList<String> getBlockedNumbers(ArrayList<String> blockedNumbers){
		
		try {
			
			FileInputStream fis = openFileInput("blockedNumbers.txt");
			DataInputStream dis = new DataInputStream(fis);
			
			int size = dis.readInt();
			
			for (int i = 0; i < size; i++){
				
				String line = dis.readUTF();
				blockedNumbers.add(line);
			}
			dis.close();
			
			return blockedNumbers;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return blockedNumbers;
		} catch (IOException e) {
			e.printStackTrace();
			return blockedNumbers;
		}
	}



    //=======================================================================
    //RETURNS LIST OF BLOCKED AREA CODES
    //=======================================================================
    public ArrayList<String> getBlockedAreaCodes(ArrayList<String> blockedAreaCodes){

        try {

            FileInputStream fis = openFileInput("blockedAreaCodes.txt");
            DataInputStream dis = new DataInputStream(fis);

            int size = dis.readInt();

            for (int i = 0; i < size; i++){

                String line = dis.readUTF();
                blockedAreaCodes.add(line);
            }
            dis.close();

            return blockedAreaCodes;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return blockedAreaCodes;
        } catch (IOException e) {
            e.printStackTrace();
            return blockedAreaCodes;
        }
    }



    //=======================================================================
    //RETURNS LIST OF ALLOWED NUMBERS
    //=======================================================================
    public ArrayList<String> getAllowedNumbers(ArrayList<String> allowedNumbers){

        try {

            FileInputStream fis = openFileInput("allowedNumbers.txt");
            DataInputStream dis = new DataInputStream(fis);

            int size = dis.readInt();

            for (int i = 0; i < size; i++){

                String line = dis.readUTF();
                allowedNumbers.add(line);
            }
            dis.close();

            return allowedNumbers;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return allowedNumbers;
        } catch (IOException e) {
            e.printStackTrace();
            return allowedNumbers;
        }
    }



    //=======================================================================
    //RETURNS LIST OF ALLOWED AREA CODES
    //=======================================================================
    public ArrayList<String> getAllowedAreaCodes(ArrayList<String> allowedAreaCodes){

        try {

            FileInputStream fis = openFileInput("allowedAreaCodes.txt");
            DataInputStream dis = new DataInputStream(fis);

            int size = dis.readInt();

            for (int i = 0; i < size; i++){

                String line = dis.readUTF();
                allowedAreaCodes.add(line);
            }
            dis.close();

            return allowedAreaCodes;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return allowedAreaCodes;
        } catch (IOException e) {
            e.printStackTrace();
            return allowedAreaCodes;
        }
    }



    //=======================================================================
    //RETURNS LOG OF BLOCKED CALLS
    //=======================================================================
    public ArrayList<String> getLog(ArrayList<String> log){

        try {

            FileInputStream fis = openFileInput("log.txt");
            DataInputStream dis = new DataInputStream(fis);

            int size = dis.readInt();

            for (int i = 0; i < size; i++){

                String line = dis.readUTF();
                log.add(line);
            }
            dis.close();

            return log;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return log;
        } catch (IOException e) {
            e.printStackTrace();
            return log;
        }
    }
	
	
	
	//=======================================================================
	//UPDATES BLOCKED NUMBERS
	//=======================================================================
	public void updateBlockedNumbers(ArrayList<String> blockedNumbers){
		
		try {

            Collections.sort(blockedNumbers);

			FileOutputStream fos = openFileOutput("blockedNumbers.txt", MODE_PRIVATE);					
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(blockedNumbers.size());//save line count
			for (String line: blockedNumbers){
				
				dos.writeUTF(line);
			}
			
			dos.flush();
			dos.close();
			
			Toast.makeText(this, "Blacklist updated", Toast.LENGTH_LONG).show();
						
		} catch (IOException e) {	
			e.printStackTrace();			
		} catch (Exception e){
			e.printStackTrace();					
		}
	}
	
	
	
	//=======================================================================
	//UPDATES BLOCKED AREA CODES
	//=======================================================================
	public void updateBlockedAreaCodes(ArrayList<String> blockedAreaCodes){
		
		try {

            Collections.sort(blockedAreaCodes);

			FileOutputStream fos = openFileOutput("blockedAreaCodes.txt", MODE_PRIVATE);					
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(blockedAreaCodes.size());//save line count
			for (String line: blockedAreaCodes){
				
				dos.writeUTF(line);
			}
			
			dos.flush();
			dos.close();
			
			Toast.makeText(this, "Blacklist updated", Toast.LENGTH_LONG).show();
						
		} catch (IOException e) {	
			e.printStackTrace();			
		} catch (Exception e){
			e.printStackTrace();					
		}
	}



    //=======================================================================
    //UPDATES ALLOWED NUMBERS
    //=======================================================================
    public void updateAllowedNumbers(ArrayList<String> allowedNumbers){

        try {

            Collections.sort(allowedNumbers);

            FileOutputStream fos = openFileOutput("allowedNumbers.txt", MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(allowedNumbers.size());//save line count
            for (String line: allowedNumbers){

                dos.writeUTF(line);
            }

            dos.flush();
            dos.close();

            Toast.makeText(this, "Whitelist updated", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    //=======================================================================
    //UPDATES ALLOWED AREA CODES
    //=======================================================================
    public void updateAllowedAreaCodes(ArrayList<String> allowedAreaCodes){

        try {

            Collections.sort(allowedAreaCodes);

            FileOutputStream fos = openFileOutput("allowedAreaCodes.txt", MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(allowedAreaCodes.size());//save line count
            for (String line: allowedAreaCodes){

                dos.writeUTF(line);
            }

            dos.flush();
            dos.close();

            Toast.makeText(this, "Whitelist updated", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    //=======================================================================
    //UPDATES LOG
    //=======================================================================
    public void updateLog(ArrayList<String> log){

        try {

            FileOutputStream fos = openFileOutput("log.txt", MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(log.size());//save line count
            for (String line: log){

                dos.writeUTF(line);
            }

            dos.flush();
            dos.close();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    //============================================================================
    //FORMATS NUMBER FOR OUTPUT
    //============================================================================
    public ArrayList<String> formatNumbers(ArrayList<String> rawNumbers){

        ArrayList<String> formattedNumbers = new ArrayList<String>();

        for (String number: rawNumbers){

            formattedNumbers.add("(" + number.substring(0,3) + ") "
                    + number.substring(3,6) + "-"
                    + number.substring(6,10));
        }

        return formattedNumbers;
    }



    //============================================================================
    //FORMATS AREA CODE FOR OUTPUT
    //============================================================================
    public ArrayList<String> formatAreaCodes(ArrayList<String> rawAreaCodes){

        ArrayList<String> formattedAreaCodes = new ArrayList<String>();

        for (String areaCode: rawAreaCodes){

            formattedAreaCodes.add("(" + areaCode + ")");
        }

        return formattedAreaCodes;
    }



    //============================================================================
    //PARSES FORMATTED PHONE NUMBER OR AREA CODE INTO RAW DATA
    //============================================================================
    public String parseNumberOrAreaCode(Object listItem){

        String item = ((String)listItem).replaceAll("[^0-9]", "");

        return item;
    }
}
