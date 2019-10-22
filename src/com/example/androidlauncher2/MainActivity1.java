package com.example.androidlauncher2;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/**
 * 
 * @author Andy
 * 
 */
public class MainActivity1 extends Activity {
	private static final String TAG = "MainActivity1";
	private String mPackageName;
	private String mName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e(TAG, "onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e(TAG, "onResume");
        Log.i("hello","hillside");
		// LockScreenActivity��ΪLauncher���Ѿ�������service
		Intent serviceIntent = new Intent();
		serviceIntent.setClass(this, LockScreenService.class);
		startService(serviceIntent);
		Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
		
		
        if(Constants.value!=0)
        startActivity(intent1);
		getLauncherPackageName(this);
		reCheckLauncher(this);

		// ����ǹر�����ҳ��״̬�°�Home��������ϵͳ��Home�����������Home���������Ч
		if (!LockScreenActivity.isStarted) {
			Intent intent = new Intent();
			intent.setComponent(new ComponentName(mPackageName, mName));
			startActivity(intent);
		}

		/*
		 * Move the task containing this activity to the back of the activity
		 * stack. The activity's order within the task is unchanged. If false
		 * then this only works if the activity is the root of a task; if true
		 * it will work for any ��Activity������android:launchMode="singleInstance"
		 */
		moveTaskToBack(false);// MainActivity��ΪHomeҳ�������õ����ȥ�����򸲸�������ҳ�ϱ�
	}

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		Log.e(TAG, "onNewIntent");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	/**
	 * �ý�������������������ҳ�������صģ���ʵҲ�������ΰ���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	// ��ȡϵͳHome�İ�������,Ĭ����ϵͳ�ģ�Ҳ�������û�ָ�����ٶ�������GO����������������
	public String getLauncherPackageName(Context context) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> res = context.getPackageManager()
				.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				ResolveInfo r = res.get(i);
				if ((r.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
					Log.e(TAG, "ϵͳHome-packageName:" + r.activityInfo.packageName);
					Log.e(TAG, "ϵͳHome-Name:" + r.activityInfo.name);

					mPackageName = r.activityInfo.packageName;
					mName = r.activityInfo.name;
					break;
				}
			}
			}
		return null;
	}

	private void reCheckLauncher(Context context) {
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		final ResolveInfo res = context.getPackageManager().resolveActivity(
				intent, 0);
		if (res.activityInfo == null) {
			return;
		}
		if (res.activityInfo.packageName.equals("android")) {
			return;
		} else {			
			return;
		}
	}

}