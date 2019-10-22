/*
 * Copyright (C) 2011 - Riccardo Ciovati
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidlauncher2;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Listener da associare ad un oggetto KeyboardView in modo tale che quando
 * viene premuto un tasto il corrispondente evento viene girato all'activity
 * passata al costruttore
 */
public class BasicOnKeyboardActionListener1 implements OnKeyboardActionListener {

	private Activity mTargetActivity;

	/***
	 * 
	 * @param targetActivity
	 *            Activity a cui deve essere girato l'evento
	 *            "pressione di un tasto sulla tastiera"
	 */
	public BasicOnKeyboardActionListener1(Activity targetActivity) {
		mTargetActivity = targetActivity;
	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		View focusCurrent = mTargetActivity.getWindow().getCurrentFocus();
	    if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
	    EditText edittext = (EditText) focusCurrent;
	    String editable = edittext.getText().toString();
		long eventTime = System.currentTimeMillis();
		KeyEvent event = new KeyEvent(eventTime, eventTime,
				KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
				KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);

		mTargetActivity.dispatchKeyEvent(event);
		
			 
			    Log.i("hell",editable);
			    if(primaryCode==66)
				{
			  	
				
			    if(editable.length()>=4)
			{
			    	Log.i("hell",mTargetActivity.toString());
			    	String r =mTargetActivity.toString();
			    	r=r.substring(0,22);
			    	if(!r.equals("com.lockscreen.Insider1"))
			    	Constants.s1=editable;
			    	else
				    	Constants.s2=editable;
			    	Intent t = new Intent(mTargetActivity.getApplicationContext(),Another_confirmation.class);
			    	mTargetActivity.startActivity(t);
			    	
			    	
			    	mTargetActivity.finish();
			    	
			}
			    else
			    {
			    	Toast.makeText(mTargetActivity.getApplicationContext(), "length too short!",
			    			   Toast.LENGTH_SHORT).show();
			    }
			    	
			
		}
	}
}