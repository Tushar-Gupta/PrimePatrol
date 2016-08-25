package com.example.tushar.primepatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		Intent intent = getIntent();
		TextView cheatTextView = (TextView) findViewById(R.id.cheatTextView);
		String cheatString = "The number is " + intent.getExtras().getString("IS_PRIME");
		cheatTextView.setText(cheatString);
	}
}
