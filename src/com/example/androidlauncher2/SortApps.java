package com.example.androidlauncher2;

import android.util.Log;

public class SortApps {
	public void exchange_sort(MainActivity.Pac[] pacs)
	{
		int i,j = 0;
		MainActivity.Pac temp;
		Log.i("hello","Pacs.length="+pacs.length+"Size of string is "+Global.sizeOfString);
		for(i=0;i<pacs.length-Global.sizeOfString-2;i++)
		{
			for(j=i+1;j<pacs.length-Global.sizeOfString-1;j++)
			{
				//Log.i("hello","j="+j);
				if(pacs[i].label.compareToIgnoreCase(pacs[j].label)>0)
				{
					temp=pacs[i];
					pacs[i]=pacs[j];
					pacs[j]=temp;
				}
			}
			//Log.i("hello","value of j="+j);
		}
		Log.i("hello","i="+i);
		Log.i("hello","j="+j);
	}
}
