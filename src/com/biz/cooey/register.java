package com.biz.cooey;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.R.bool;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends FragmentActivity{
	
	private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.register, container, false);

	    return view;
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
        	View decorView = getWindow().getDecorView();
        	// Hide the status bar.
        	int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        	decorView.setSystemUiVisibility(uiOptions);
        	// Remember that you should never show the action bar if the
        	// status bar is hidden, so hide that too if necessary.
        	ActionBar actionBar = getActionBar();
        	actionBar.hide();
        }
        
        setContentView(R.layout.register);
        Spinner sp=(Spinner)findViewById(R.id.blood_spinner);
       
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> av, View v,
					int position, long itemId) {
				// TODO Auto-generated method stub
				String item=av.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(),"Selected Item is "+item,Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> av) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_launch, menu);
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    

        return super.onOptionsItemSelected(item);
    }
	
	public void signUpBtnClicked(View view)
	{
		signUpValidations();
	}
	
	public void signUpValidations()
	{
		EditText emailText = (EditText) findViewById(R.id.email);
		EditText mobileText = (EditText) findViewById(R.id.mobile);
		EditText cityText = (EditText) findViewById(R.id.city);
		EditText countryText = (EditText) findViewById(R.id.country);
		if(emailText.getText().length() == 0)
		{
			emailText.setError("Please enter a valid email address");
		}
		else
		{
			if(!validateEmail(emailText.getText().toString()))
			{
				emailText.setError("Please give us a valid email address");
			}
			if(mobileText.getText().length() < 6)
			{
				mobileText.setError("Please enter a valid mobile number");
			}
			else if(cityText.getText().length() == 0)
			{	
				cityText.setError("Please enter a valid City name");
			}
			else if(countryText.getText().length() == 0)
			{
				countryText.setError("Please enter a valid Country name");
			}
			else
			{
//				Spinner sp=(Spinner)findViewById(R.id.blood_spinner);
//				String blood_group = sp.getSelectedItem().toString();
//				registerUser(mobileText.getText().toString(), emailText.getText().toString(),cityText.getText().toString(), countryText.getText().toString(),blood_group, "M","08-01-1991" );
//				
				Intent onboardingScreen = new Intent(getApplicationContext(), onboarding.class);
			    startActivity(onboardingScreen);
			}
		}
		
	}
	
	public void registerUser(String MobileNumber, String Email, String City, String Country, String BloodGroup, String Gender, String DOB)
	{
		
		
		
		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();
		 
		// Creating HTTP Post
		HttpPost httpPost = new HttpPost("http://asl002.cloudapp.net:9010/ehealth/v1/profile/patient");
		httpPost.setHeader("Content-Type","application/json");
		
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("firstName", "Ravindra"));
		nameValuePair.add(new BasicNameValuePair("lastName", "Naik"));
		nameValuePair.add(new BasicNameValuePair("email", Email));
		nameValuePair.add(new BasicNameValuePair("city", City));
		nameValuePair.add(new BasicNameValuePair("gender", Gender));
		nameValuePair.add(new BasicNameValuePair("DOB", DOB));
		nameValuePair.add(new BasicNameValuePair("country", Country));
		nameValuePair.add(new BasicNameValuePair("TID", "TEST"));
		nameValuePair.add(new BasicNameValuePair("trackingId", "Ravi"));
		nameValuePair.add(new BasicNameValuePair("profilePic", "https://plus.google.com/+ravindranaik32/posts?pid=6039839085769037570&oid=111018437249161254947"));
		
		// Url Encoding the POST parameters
		try {
		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		}
		catch (UnsupportedEncodingException e) {
		    // writing error to Log
		    e.printStackTrace();
		}
		
		// Making HTTP Request
		try {
		    HttpResponse response = httpClient.execute(httpPost);
		 
		    // writing response to log
		    System.out.println(response.toString());
		    Log.d("Http Response:", response.toString());
		    if(response.toString() == "200")
		    {
		    	Intent onboardingScreen = new Intent(getApplicationContext(), onboarding.class);
			    startActivity(onboardingScreen);
		    }
		    else
		    {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(register.this);

        		// 2. Chain together various setter methods to set the dialog characteristics
        		builder.setMessage("Something went wrong, please try again").setTitle("OOPS");

        		// 3. Get the AlertDialog from create()
        		AlertDialog dialog = builder.create();
        		
        		dialog.show();
		    }
		 
		} catch (ClientProtocolException e) {
		    // writing exception to log
		    e.printStackTrace();
		         
		} catch (IOException e) {
		    // writing exception to log
		    e.printStackTrace();
		}
	}
	
	public static boolean validateEmail(String email)
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
	}
	

	
	public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
  
	    

}
