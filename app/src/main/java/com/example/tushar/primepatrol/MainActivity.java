package com.example.tushar.primepatrol;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String USER_NAME = "com.example.tushar.primepatrol.USER_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Start button */
    public void startQuiz(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        String usernamestring = username.getText().toString();
        if (usernamestring.length()>0)
        {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra(USER_NAME, usernamestring);
            startActivity(intent);
        }
        else
		{
			Context context = getApplicationContext();
			CharSequence text = "Please enter a valid name";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
    }

}
