package com.biz.cooey;

import android.R.bool;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;

@SuppressLint("NewApi")
public class home extends Activity{
	public static final String PREFS_NAME = "MyPrefsFile";
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setTitle("");
	        setContentView(R.layout.home);
	        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	        
	        boolean value = settings.getBoolean("BPMeterAdded", false);
	       TableLayout datype=(TableLayout)findViewById(R.id.bpMeter);
	        if(value != true)
	        {
	            datype.setVisibility(View.GONE);
	        }
	        else 
	        {
	        	datype.setVisibility(View.VISIBLE);
	        } 

	        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));   
	        
	    }
	 
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        
	        getMenuInflater().inflate(R.menu.app_launch, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	 
	    	switch (item.getItemId()) {
	        case R.id.add_BpTracker:
	        	AddBpTracker();
	            return true;
	     
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    }
	    
	    public void AddBpTracker()
	    {
	    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	        SharedPreferences.Editor editor = settings.edit();
	        editor.putBoolean("BPMeterAdded", true);
	        editor.commit();
	        
	        String value = settings.getString("silentMode", "");
	       
	    	System.out.println("You are trying to add bluetooth");
	    }


}
