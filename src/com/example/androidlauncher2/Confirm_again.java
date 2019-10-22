package com.example.androidlauncher2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Confirm_again extends Activity {
	Button b;
	EditText et1;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.confirm1);
		b=(Button)findViewById(R.id.button_okay_confirm);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				et1=(EditText)findViewById(R.id.editText_confirm);
				String h = et1.getText().toString();
				if(h.equals(Constants.ans))
				{
					Constants.s1="0000";
					Toast.makeText(getApplicationContext(), "Master Pin is now 0000", Toast.LENGTH_SHORT).show();
					finish();
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_LONG).show();

				}
				
			}
		});
	}
	
	
}
