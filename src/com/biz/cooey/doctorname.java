package com.biz.cooey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class doctorname extends FragmentActivity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorname);
 
     
 
    }
	
	public void skipBtnClicked(View view){
   	 try
		 {
			 Intent addDoctorName = new Intent(getApplicationContext(), home.class);
		     startActivity(addDoctorName);
		 }
		 catch(Exception ex)
		 {
			System.out.println(ex.getMessage()); 
		 }
   }
   
   public void AddDoctorBtnClicked(View view)
   {
	   	System.out.println("You are here in add doctor");
	   	Intent homeScreen = new Intent(getApplicationContext(), home.class);
	    startActivity(homeScreen);
  
   }

}
