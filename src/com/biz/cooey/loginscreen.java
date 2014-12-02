package com.biz.cooey;

import java.util.Arrays;

import com.facebook.android.Facebook;
import com.facebook.widget.LoginButton;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment ;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.os.Build;
import android.os.Bundle;



public class loginscreen extends FragmentActivity{
	
	
	 
    
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
//		private MainFragment mainFragment;
//		 if (savedInstanceState == null) {
//		        // Add the fragment on initial activity setup
//		        mainFragment = new MainFragment();
//		        getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment)
//		        .commit();
//		    } else {
//		        // Or set the fragment from restored state info
//		        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
//		    }
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

	 public void SignUpWithEmail(View view){
		 
		 try
		 {
			 Intent newUser = new Intent(getApplicationContext(), register.class);
		     startActivity(newUser);
		 }
		 catch(Exception ex)
		 {
			System.out.println(ex.getMessage()); 
		 }
		 
		 
		 
	 }
	 
//	 public void fbData(View view) {
//			final String[] PERMISSIONS = new String[] { "photo_upload",
//					"user_photos", "publish_stream", "read_stream",
//			"offline_access", "publish_actions" };
//			
//			LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
//			authButton.setFragment(this);
//			authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
//			facebook.authorize(this, PERMISSIONS, new DialogListener() {
//
//				public void onComplete(Bundle values) {
//
//					JSONObject me;
//					try {
//						me = new JSONObject(facebook.request("me"));
//						Constants.FACEBOOK_USER_ACCESS_ID = facebook
//								.getAccessToken();
//						Constants.FACEBOOK_USER_ID = (String) me.getString("id");
//						Constants.FACEBOOK_USER_NAME = (String) me
//								.getString("name");
//						Constants.fbShareEnabled = true;
//						prefsEditor.putBoolean("fbShareEnabled", true);
//						prefsEditor.putString("fbId", Constants.FACEBOOK_USER_ID);
//						prefsEditor.putString("fbAccessId",
//								Constants.FACEBOOK_USER_ACCESS_ID);
//						prefsEditor.putString("fbUserName",
//								Constants.FACEBOOK_USER_NAME);
//						prefsEditor.commit();
//						
//					} catch (Exception e1) {
//
//					}
//
//				}
//			}
//	
//}
}
