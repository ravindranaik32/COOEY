package com.biz.cooey;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class home extends Activity{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.home);
	        
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


}
