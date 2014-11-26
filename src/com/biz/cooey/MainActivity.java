package com.biz.cooey;

import com.facebook.android.Facebook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.os.Build;
import android.os.Bundle;



public class MainActivity extends FragmentActivity{
	
	 private String TAG = "MainActivity";
	 
    
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.loginscreen, container, false);

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
	        setContentView(R.layout.loginscreen);
	 }
	 
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.app_launch, menu);
	        return true;
	    }
	 
	 public boolean onOptionsItemSelected(MenuItem item) {
	    

	        return super.onOptionsItemSelected(item);
	    }

	 public void LoginWithFb(){
		 
		 
	 }
	
}
