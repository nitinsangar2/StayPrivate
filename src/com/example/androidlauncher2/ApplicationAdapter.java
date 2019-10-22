package com.example.androidlauncher2;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
	LayoutInflater inflater;
	
	private List<ApplicationInfo> appsList = null;
	private Context context;
	private PackageManager packageManager;
	int k=0;
	public ApplicationAdapter(Context context, int textViewResourceId,List<ApplicationInfo> appsList) {
		super(context, textViewResourceId, appsList);
		this.context = context;
		this.appsList = appsList;
		packageManager = context.getPackageManager();
		inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		Global.checkBoxState=new boolean[appsList.size()];
		Global.hiding=new String[appsList.size()];
		Global.sizeOfString=0;
	}

	@Override
	public int getCount() {
		return ((null != appsList) ? appsList.size() : 0);
	}

	@Override
	public ApplicationInfo getItem(int position) {
		return ((null != appsList) ? appsList.get(position) : null);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final MyViewHolder mviewholder;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.customlist,parent,false);
			mviewholder = new MyViewHolder();
			view.setTag(mviewholder);
		}
		else
		{
			mviewholder=(MyViewHolder)view.getTag();
		}
		
		final ApplicationInfo data = appsList.get(position);
		if (data != null) {
			mviewholder.appName = (TextView) view.findViewById(R.id.app_name);
			mviewholder.packageName = (TextView) view.findViewById(R.id.app_paackage);
			mviewholder.iconview = (ImageView) view.findViewById(R.id.app_icon);
			mviewholder.checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
			mviewholder.checkBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(mviewholder.checkBox.isChecked())
					{
						Global.checkBoxState[position]=true;
						Global.hiding[k++]=data.packageName.toString();
						Global.sizeOfString++;
						
				}
					else
					{
						Global.checkBoxState[position]=false;
						Global.hiding[k]=null;
						k--;
						Global.sizeOfString--;
					}
				}
			});
			if(Global.checkBoxState[position]==true){
				mviewholder.checkBox.setChecked(true);}
	        else{
	        	mviewholder.checkBox.setChecked(false);
	        }
			mviewholder.appName.setText(data.loadLabel(packageManager));
			mviewholder.packageName.setText(data.packageName);
			mviewholder.iconview.setImageDrawable(data.loadIcon(packageManager));
			
		}
		return view;
	}
	public class MyViewHolder {
		ImageView iconview;
		TextView appName, packageName;
		CheckBox checkBox;
	}
	
};
