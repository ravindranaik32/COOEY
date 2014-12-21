package com.biz.cooey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectionFragment extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    super.onCreateView(inflater, container, savedInstanceState);
	    View view = inflater.inflate(R.layout.selection, container, false);
	    
	    goToHomeScreen(view);
	    
	    return view;
	}
	
	public void goToHomeScreen(View view)
	{
		 try
		 {
			 Intent newUser = new Intent(getActivity().getApplicationContext(), onboarding.class);
		     startActivity(newUser);
		 }
		 catch(Exception ex)
		 {
			System.out.println(ex.getMessage()); 
		 }
	}
	
	
}
