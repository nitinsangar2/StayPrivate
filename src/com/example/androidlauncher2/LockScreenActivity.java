package com.example.androidlauncher2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class LockScreenActivity extends Activity  {

	private static final String TAG = "LockScreenActivity";
	public static boolean isStarted=false;
	private CustomKeyboardView mKeyboardView;
	private View mTargetView,textview;
	private Keyboard mKeyboard;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
		isStarted=true;
		Constants.value=0;
	
		mKeyboard = new Keyboard(this, R.layout.keyboard);
		mTargetView = (EditText) findViewById(R.id.target);
		textview=(TextView)findViewById(R.id.target1);
		textview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(),Confirm_again.class);
			startActivity(i);
			}
		});
		
		mTargetView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Dobbiamo intercettare l'evento onTouch in modo da aprire la
				// nostra tastiera e prevenire che venga aperta quella di
				// Android
				showKeyboardWithAnimation();
				return true;
			}
		});

		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this));
		
		
		
	}

	/***
	 * Mostra la tastiera a schermo con una animazione di slide dal basso
	 */
	private void showKeyboardWithAnimation() {
		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(LockScreenActivity.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		}
	}

	

	@Override
	protected void onPause() {
		super.onPause();
		//finish();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
	public boolean dispatchKeyEvent(KeyEvent event) {
	    final int keycode = event.getKeyCode();
	    final int action = event.getAction();
	    if (keycode == KeyEvent.KEYCODE_MENU && action == KeyEvent.ACTION_UP) {
	        return true; // consume the key press
	    }
	    return super.dispatchKeyEvent(event);
	}
	
	public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);


	    Log.d("Focus debug", "Focus changed !");

	if(!hasFocus) {
	    Log.d("Focus debug", "Lost focus !");

	    Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
	    sendBroadcast(closeDialog);
	
	}
	
	    
	}
	

	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_HOME)) {
			// Key code constant: Home key. This key is handled by the framework
			// and is never delivered to applications.
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onAttachedToWindow() {
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);// Android4.0Ò»ÏÂÆÁ±ÎHome¼ü
		super.onAttachedToWindow();
	}
	

	@Override
	public void onBackPressed() {
		
		return;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	 class StateListener extends PhoneStateListener{
	        @Override
	        public void onCallStateChanged(int state, String incomingNumber) {

	            super.onCallStateChanged(state, incomingNumber);
	            switch(state){
	                case TelephonyManager.CALL_STATE_RINGING:
	                    break;
	                case TelephonyManager.CALL_STATE_OFFHOOK:
	                    System.out.println("call Activity off hook");
	                	finish();



	                    break;
	                case TelephonyManager.CALL_STATE_IDLE:
	                    break;
	            }
	        }
	    };

	/**
	 * Õð¶¯
	 */
	private void virbate() {
		Vibrator vibrator = (Vibrator) this
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
	}
	
}