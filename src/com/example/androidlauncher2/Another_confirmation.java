package com.example.androidlauncher2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Another_confirmation extends Activity {

	Button b;
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.confirm);
		b=(Button)findViewById(R.id.button695);
		et=(EditText)findViewById(R.id.editText1090);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Constants.ans=et.getText().toString();
				if(!Constants.ans.equals(""))
				{Toast.makeText(getApplicationContext(), "Response recorded Successfully!",
		    			   Toast.LENGTH_SHORT).show();
				finish();}
				else
					Toast.makeText(getApplicationContext(), "Please provide the answer!",
			    			   Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}
