package com.example.androidlauncher2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class AppClickListener implements OnClickListener{
	
	MainActivity.Pac[] pacsForListener;
	Context mContext;
	public AppClickListener (MainActivity.Pac[] pacs,Context ctxt)
	{
		pacsForListener=pacs;
		mContext=ctxt;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String[] data;
		data=(String[])v.getTag();
		Intent launchIntent=new Intent(Intent.ACTION_MAIN);
		launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName cp=new ComponentName(data[0],data[1]);
		launchIntent.setComponent(cp);
		mContext.startActivity(launchIntent);
			
		
	}

}
