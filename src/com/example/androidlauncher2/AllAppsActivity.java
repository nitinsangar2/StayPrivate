package com.example.androidlauncher2;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AllAppsActivity extends ListActivity {
	private PackageManager packageManager = null;
	private List<ApplicationInfo> applist = null;
	
	Button hide,cancel;
	
	private ApplicationAdapter listadaptor = null;
	String arr[]=new String[1000];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		hide=(Button)findViewById(R.id.hide);
		cancel=(Button)findViewById(R.id.cancel);
		packageManager = getPackageManager();
		//packageManager.setComponentEnabledSetting(, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
		new LoadApplications().execute();
		Global.sizeOfString=0;
		hide.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				int i,k=0;
				Log.i("hello","value of size is "+Global.hiding.length);
				if(Global.sizeOfString==0)
				{
					Toast.makeText(getApplicationContext(), "Select Apps To Hide", Toast.LENGTH_SHORT).show();
				}
				else
				{
				for(i=0;i<Global.sizeOfString;i++)
				{
					arr[i]=Global.hiding[i];
				
				}
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				
		         intent.putExtra("sending", arr);
		         
				}
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int i;
				// TODO Auto-generated method stub
				for(i=0;i<Global.checkBoxState.length;i++)
					Global.checkBoxState[i]=false;
				setListAdapter(listadaptor);
				
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	new AlertDialog.Builder(this)
			.setTitle("Alert!")
			.setMessage("Are you sure you want to exit?")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton("Quit", new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	
			    	finish();
			    	Intent startMain = new Intent(Intent.ACTION_MAIN);
			    	startMain.addCategory(Intent.CATEGORY_HOME);
			    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    	startActivity(startMain);
			    }})
			.setNegativeButton("Return", null ).show();

	       
	    }
	    return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = true;

		switch (item.getItemId()) {
		case R.id.menu_settings: 
		{
			displayAboutDialog();
			break;
		}
		default:
		{
			result = super.onOptionsItemSelected(item);

			break;
		}
		}

		return result;
	}

	private void displayAboutDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Hey");
		builder.setMessage("Getting data");

		builder.setPositiveButton("Know More", new DialogInterface.OnClickListener() {
		       public void onClick(DialogInterface dialog, int id) {
		    	   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://javatechig.com"));
		    	   startActivity(browserIntent);
		    	   dialog.cancel();
		       }
		   });
		builder.setNegativeButton("No Thanks!", new DialogInterface.OnClickListener() {
		       public void onClick(DialogInterface dialog, int id) {
		            dialog.cancel();
		       }
		});

		builder.show();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		ApplicationInfo app = applist.get(position);
		try {
			Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);//To launch App
			
			if (null != intent) {
				startActivity(intent);
			}
		} catch (ActivityNotFoundException e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
		ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
		int i=0;
		//Log.i("hello","appList isss"+applist);
		for (ApplicationInfo info : list) {
			try {
				if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
					//String str=info.toString().substring(25);
					applist.add(info);
					i++;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		/*Intent intent = new Intent(AllAppsActivity.this,MainActivity.class);
         intent.putExtra("sending", arr);
         startActivity(intent);
	*/
		return applist;
	}

	private class LoadApplications extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;

		@Override
		protected Void doInBackground(Void... params) {
			applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
			listadaptor = new ApplicationAdapter(AllAppsActivity.this,R.layout.customlist, applist);

			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Void result) {
			setListAdapter(listadaptor);
			progress.dismiss();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() 
		{
			progress = ProgressDialog.show(AllAppsActivity.this, null,"Loading application info...");
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	}
}