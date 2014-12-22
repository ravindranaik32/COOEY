package com.biz.cooey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import com.biz.cooey.R.string;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
 
public class onboarding extends FragmentActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding);
 
 
    }
    
    public void skipBtnClicked(View view){
    	 try
		 {
    		
			 Intent addDoctorName = new Intent(getApplicationContext(), doctorname.class);
		     startActivity(addDoctorName);
		 }
		 catch(Exception ex)
		 {
			System.out.println(ex.getMessage()); 
		 }
    }
    
    public void AddMedicineBtnClicked(View view)
    {
    	EditText medicine = (EditText)findViewById(R.id.medicineName);
    	String medicineName = medicine.getText().toString();
    	
    	EditText purpose = (EditText)findViewById(R.id.used_for);
    	String usedFor = purpose.getText().toString();
    	if(medicineName.length() != 0)
    	{
    		if(usedFor.length() != 0)
    		{
    			
//    			Void params = null;
//				new FetchTask().execute(params);
    			
    			Intent doctorScreen = new Intent(getApplicationContext(), doctorname.class);
			    startActivity(doctorScreen);
    			
    		}
    		else
    		{
    			AlertDialog.Builder builder = new AlertDialog.Builder(onboarding.this);

        		// 2. Chain together various setter methods to set the dialog characteristics
        		builder.setMessage("Please fill the purpose").setTitle("OOPS");

        		// 3. Get the AlertDialog from create()
        		AlertDialog dialog = builder.create();
        		
        		dialog.show();
    		}
    	}
    	else
    	{
    		AlertDialog.Builder builder = new AlertDialog.Builder(onboarding.this);

    		// 2. Chain together various setter methods to set the dialog characteristics
    		builder.setMessage("Please fill the medicine name").setTitle("OOPS");

    		// 3. Get the AlertDialog from create()
    		AlertDialog dialog = builder.create();
    		
    		dialog.show();
    	}
    	
    	
    	
   
    }
    
    public class FetchTask extends AsyncTask<Void, Void, JSONArray> {
        protected JSONArray doInBackground(Void... params) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://asl002.cloudapp.net:9010/ehealth/v1/actvity/medication");
                
                EditText medicine = (EditText)findViewById(R.id.medicineName);
            	String medicineName = medicine.getText().toString();
            	
            	EditText purpose = (EditText)findViewById(R.id.used_for);
            	String usedFor = purpose.getText().toString();
            	
            	InputStream inputStream = null;
                String result = "";

                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8);
                nameValuePairs.add(new BasicNameValuePair("name", medicineName));
                nameValuePairs.add(new BasicNameValuePair("used_for", usedFor));
                nameValuePairs.add( new BasicNameValuePair("strength", ""));
                nameValuePairs.add( new BasicNameValuePair("strength_unit", ""));
                nameValuePairs.add( new BasicNameValuePair("related_specality", ""));
                nameValuePairs.add( new BasicNameValuePair("base_component", ""));
                nameValuePairs.add( new BasicNameValuePair("TID", ""));
                nameValuePairs.add( new BasicNameValuePair("trackingId", ""));

                // Execute HTTP Post Request
                HttpResponse newresponse = httpclient.execute(httppost);
                
                inputStream = newresponse.getEntity().getContent();
                
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";
                
                System.out.println(result);

                BufferedReader reader = new BufferedReader(new InputStreamReader(newresponse.getEntity().getContent(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                sb.append(reader.readLine() + "\n");
                String line = "0";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                reader.close();
                String result11 = sb.toString();

                // parsing data
                return new JSONArray(result11);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            if (result != null) {
                System.out.println("You are finally here");
            } else {
                // error occured
            }
        }
    }
    public void callWebService(String medicineName, String usedFor){  
    	HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://asl002.cloudapp.net:9010/ehealth/v1/actvity/medication");
        InputStream inputStream = null;
        String result = "";
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8);
            nameValuePairs.add(new BasicNameValuePair("name", medicineName));
            nameValuePairs.add(new BasicNameValuePair("used_for", usedFor));
            nameValuePairs.add( new BasicNameValuePair("strength", ""));
            nameValuePairs.add( new BasicNameValuePair("strength_unit", ""));
            nameValuePairs.add( new BasicNameValuePair("related_specality", ""));
            nameValuePairs.add( new BasicNameValuePair("base_component", ""));
            nameValuePairs.add( new BasicNameValuePair("TID", ""));
            nameValuePairs.add( new BasicNameValuePair("trackingId", ""));
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse newresponse = httpclient.execute(httppost);
            
            inputStream = newresponse.getEntity().getContent();
            
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            
            System.out.println(result);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        
    }
    
    public String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }   
 
  
}