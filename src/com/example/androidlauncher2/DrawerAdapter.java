package com.example.androidlauncher2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter{

	MainActivity.Pac[] pacsForAdapter;
	Context mContext;
	public DrawerAdapter(Context c,MainActivity.Pac pacs[])
	{
		mContext=c;
		pacsForAdapter=pacs;
		Log.i("hello","pacsForAdapter="+pacsForAdapter.length);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pacsForAdapter.length-Global.sizeOfString;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	static class ViewHolder
	{
		TextView text;
		ImageView icon;
	}
	@Override
	public View getView(int pos, View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		View view=convertView;
		final ViewHolder viewHolder;
		if(view==null)
		{
			LayoutInflater li=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//view = li.inflate(R.layout.customlist,parent,false);
			view=li.inflate(R.layout.drawer_item, parent,false);
			
			viewHolder=new ViewHolder();
			view.setTag(viewHolder);
		}
		else
			viewHolder=(ViewHolder)view.getTag();
		if(pacsForAdapter[pos]!=null)
		{
			Log.i("hello","pos is "+pos+"pacsForAdapter[pos].label="+pacsForAdapter[pos].label);
			viewHolder.text=(TextView)view.findViewById(R.id.icon_text);
			viewHolder.icon=(ImageView)view.findViewById(R.id.icon_image);
			viewHolder.text.setText(pacsForAdapter[pos].label);
			viewHolder.icon.setImageDrawable(pacsForAdapter[pos].icon);
		}
		return view;
	}
	

}
