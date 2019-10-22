package com.example.androidlauncher2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class Insider1 extends Activity {
	private CustomKeyboardView mKeyboardView;
	private View mTargetView;
	private Keyboard mKeyboard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		mKeyboard = new Keyboard(this, R.layout.keyboard);
		mTargetView = (EditText) findViewById(R.id.target);
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
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener1(
						this));
	}

	/***
	 * Mostra la tastiera a schermo con una animazione di slide dal basso
	 */
	private void showKeyboardWithAnimation() {
		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(Insider1.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		}
	}

	

}
	

