package com.biz.cooey;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.lifesense.ble.bean.LsDeviceInfo;

public class PairResultsActivity extends Activity {

	private TextView infoTextView;
	private ContentValues contentValues;
	private ContentResolver contentResolver;
	
	private LsDeviceInfo pairedLsDevice;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pair_results);
		infoTextView =(TextView) findViewById(R.id.pairResultsView);
		contentResolver=this.getContentResolver();
		contentValues=new ContentValues();
		
		pairedLsDevice=getIntent().getParcelableExtra("LsDeviceInfo");
		showPairResults(pairedLsDevice); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pair_results, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	   
	    overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left );
	}
	
	public void doClick(View view)
	{
		switch (view.getId()) 
		{
			case R.id.backBtn:
			{
				showPromptDialog("Prompt", "Do you want to save the paired results?");
				}break;
			case R.id.saveBtn:
			{
				showPromptDialog("Prompt", "Do you want to save the paired results?");
				
				}break;
		}
	}
	
	private void showPromptDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				PairResultsActivity.this).setTitle(title)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						savePairedDevice(pairedLsDevice);
					
						Intent intent = new Intent(PairResultsActivity.this, home.class);
					    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
					    startActivity(intent);
					}

				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						onBackPressed();
					}		
				}).setMessage(message);
		builder.create().show();
	}
	private void showPairResults(LsDeviceInfo device) 
	{
		 if(device!=null)
   	   {        		  
   		   infoTextView.append("***********Successfully Paired************"+"\n");
   		   infoTextView.append("deviceName: "+device.getDeviceName()+"\n");
   		   infoTextView.append("broadcastID: "+device.getBroadcastID()+"\n");
   		   infoTextView.append("deviceType: "+device.getDeviceType()+"\n"); 
   		   infoTextView.append("password: "+device.getPassword()+"\n"); 
   		   infoTextView.append("deviceID: "+device.getDeviceId()+"\n");
       	   infoTextView.append("deviceSN: "+device.getDeviceSn()+"\n");
       	   infoTextView.append("manufactureName: "+device.getManufactureName()+"\n");	        	
       	   infoTextView.append("modelNumber: "+device.getModelNumber()+"\n");  	      	  
       	   infoTextView.append("firmwareVersion: "+device.getFirmwareVersion()+"\n");
       	   infoTextView.append("hardwareVersion: "+device.getHardwareVersion()+"\n");   
       	   infoTextView.append("softwareVersion: "+device.getSoftwareVersion()+"\n");
       	   infoTextView.append("UserNumber: "+device.getDeviceUserNumber()+"\n");
       	   infoTextView.append("maxUserQuantity: "+device.getMaxUserQuantity()+"\n");
       	   infoTextView.append("protocolType: "+device.getProtocolType()+"\n");
   	   		}
   	   else
   	   {
   		  infoTextView.append("Failed paired!Please try again"+"\n");
   	   		}	 	       
	}
	
	private void savePairedDevice(LsDeviceInfo device)
	{
		if(device!=null)
		{	
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_NAME, device.getDeviceName());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_BROCASTID, device.getBroadcastID());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_FIRMWARE_VERSION, device.getFirmwareVersion());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_HARDWARE_VERSION, device.getHardwareVersion());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_ID, device.getDeviceId());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_MANUFACTURENAME, device.getManufactureName());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_MODELNUMBER, device.getModelNumber());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_PASSWORD, device.getPassword());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_SN, device.getDeviceSn());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_SOFTWARE_VERSION, device.getSoftwareVersion());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_SYSTEMID, device.getSystemId());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_TYPE,device.getDeviceType());
			contentValues.put(DatabaseContentProvider.KEY_DEVICE_USER_NUMBER,device.getDeviceUserNumber());
			contentValues.put(DatabaseContentProvider.KEY_MAX_USER_QUANTITY,device.getMaxUserQuantity());
			contentValues.put(DatabaseContentProvider.KEY_PROTOCOL_TYPE,device.getProtocolType());
	
			
			
			
			contentResolver.insert(DatabaseContentProvider.CONTENT_URI, contentValues);
			}	
	}
	
}
