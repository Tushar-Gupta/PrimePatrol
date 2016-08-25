package com.example.tushar.primepatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hint);
		Intent intent = getIntent();
//		ViewGroup layout = (ViewGroup) findViewById(R.id.activity_hint);
		String hintString;
		TextView hintTextView = (TextView) findViewById(R.id.hintTextView);
		if ( (intent.getIntExtra("CURRENT_NUMBER",0))%2 == 0 )
		{
			hintString = "The number is even.";
		}
		else
		{
			hintString = "The number is odd.";
		}
		hintTextView.setText(hintString);
	}
}
