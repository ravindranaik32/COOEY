package com.biz.cooey;


import com.facebook.AppEventsLogger;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


public class App_launch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        	
        	ActionBar actionBar = getActionBar();
        	actionBar.hide();
        }
        setContentView(R.layout.activity_app_launch);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.app_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        return super.onOptionsItemSelected(item);
    }
    
    public void Register(View view){
    	
    	Intent registerScreen = new Intent(getApplicationContext(), loginscreen.class);
    	startActivity(registerScreen);
    }
    
    public void Login(View view){
    
    }
    
    protected void onResume() {
    	  super.onResume();

    	  // Logs 'install' and 'app activate' App Events.
    	  AppEventsLogger.activateApp(this);
    	}
    
}
