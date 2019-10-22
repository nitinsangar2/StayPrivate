package com.example.androidlauncher2;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class MainActivity extends Activity {
	
	DrawerAdapter drawerAdapterObject;
	GridView drawerGrid;
	SlidingDrawer slidingDrawer;
	RelativeLayout homeView;
	class Pac
	{
		Drawable icon;
		String packageName,label,name;
	}
	Pac[] pacs;
	Bundle bundle=null;
	String[] received=new String[1000];
	PackageManager pm;
	static boolean appLaunchable=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawerGrid=(GridView)findViewById(R.id.content);
		slidingDrawer=(SlidingDrawer)findViewById(R.id.drawer);
		homeView=(RelativeLayout)findViewById(R.id.home_view);
		bundle = getIntent().getExtras();//getting data from Main activity
		//to pass data of one activity to another
		if (bundle != null)
		{
			 received= bundle.getStringArray("sending");
		}
		pm=getPackageManager();
		set_pacs();
		slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				appLaunchable=true;
				// TODO Auto-generated method stub
				
			}
		});
		IntentFilter filter=new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package");
		registerReceiver(new PacReceiver(), filter);
	}
	public void set_pacs()
	{
		
		final Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		pacs=new Pac[pacsList.size()];
		int k=0,j;
		int c=0;
		for(int i=0;i<pacsList.size();i++)
		{
			Log.i("hello","i="+i);
			int flag=0;
			String str;
			str=pacsList.get(i).activityInfo.packageName;
			if(bundle!=null && Constants.flag!=1)
			{
				Log.i("hello","hey bundle is not null");
				for(j=0;j<Global.sizeOfString;j++)
				{
					if(str.equals(received[j]))
					{
						flag=1;
						break;
					}
				}
			}
			if(flag==0)
			{
				Log.i("hello","bundle is null");
				pacs[k]=new Pac();
				pacs[k].packageName=pacsList.get(i).activityInfo.packageName;
				pacs[k].icon=pacsList.get(i).loadIcon(pm);
				pacs[k].name=pacsList.get(i).activityInfo.name;
				pacs[k].label=pacsList.get(i).loadLabel(pm).toString();
				Log.i("hello","pacs[k].label"+pacs[k].label);
				k++;
			}
			
		}
		Log.i("hello","size of string is "+Global.sizeOfString);
		Log.i("hello","k="+k);
		new SortApps().exchange_sort(pacs);
		Log.i("hello","size of string is "+Global.sizeOfString);
		drawerAdapterObject=new DrawerAdapter(this, pacs);
		drawerGrid.setAdapter(drawerAdapterObject);
		drawerGrid.setOnItemClickListener(new DrawerClickListener(this, pacs, pm));
		drawerGrid.setOnItemLongClickListener(new DrawerLongClickListener(this,slidingDrawer,homeView,pacs));
		
	}
	public class PacReceiver extends BroadcastReceiver
	{
			
		@Override
		public void onReceive(Context context, Intent intent) 
		{
			// TODO Auto-generated method stub
			//Log.i("hello","sachinasnknasdlkndflaksnmf");
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	return true;
	    }
	    return true;
	}

	

}
