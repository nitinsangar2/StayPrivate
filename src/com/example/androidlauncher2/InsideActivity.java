package com.example.androidlauncher2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InsideActivity extends Activity {
	
Button but1,but2,but3;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inside_xml);
		but1=(Button)findViewById(R.id.button100);
	
		but1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			Constants.a=1;
			Intent intent = new Intent(getApplicationContext(),Confirmation_class.class);
			startActivity(intent);
			}
		});
	but2=(Button)findViewById(R.id.button200);
		but2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.a=2;
			Intent intent1= new Intent(getApplicationContext(),Confirmation_class.class);
			startActivity(intent1);
			}
		});
		but3=(Button)findViewById(R.id.button_forgot);
		but3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1= new Intent(getApplicationContext(),Confirm_again.class);
				startActivity(intent1);
				
				
			}
		});
		
	}
}
