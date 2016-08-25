package com.example.tushar.primepatrol;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
	protected int currentNumber;
	protected int hintScene;
	protected int cheatScene;
	//checks whether an int is prime or not.
	protected int isPrime(int n) {
		//check if n is a multiple of 2
		if (n%2==0) return 0;
		//if not, then just check the odds
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return 0;
		}
		return 1;
	}

	protected void showGreeting(String greetingString)
	{
		TextView greetingTextView = (TextView) findViewById(R.id.greetingTextView);
		greetingString = "Hello " + greetingString + ",";
		greetingTextView.setText(greetingString);
	}

	protected void showFirstQuestion()
    {
		((Button) findViewById(R.id.button_hint)).setText("Hint");
		((Button) findViewById(R.id.button_cheat)).setText("Cheat");
        TextView questionTextView = (TextView) findViewById(R.id.questionTextView);
        questionTextView.setTextSize(30);
        Random rand = new Random();
        int  generatedNumber = rand.nextInt(1000) + 1;
        String questionString = "Is " + Integer.toString(generatedNumber) + " prime?";
        questionTextView.setText(questionString);
		this.currentNumber = generatedNumber;
		this.hintScene = 0;
		this.cheatScene = 0;
    }


	public void nextQuestion(View view)
	{
		this.hintScene = 0;
		this.cheatScene = 0;
		((Button) findViewById(R.id.button_hint)).setEnabled(true);
		((Button) findViewById(R.id.button_cheat)).setEnabled(true);
		((Button) findViewById(R.id.button_hint)).setText("Hint");
		((Button) findViewById(R.id.button_cheat)).setText("Cheat");
		((Button) findViewById(R.id.button_next)).setEnabled(true);
		((Button) findViewById(R.id.button_yes)).setEnabled(true);
		((Button) findViewById(R.id.button_no)).setEnabled(true);
		TextView questionTextView = (TextView) findViewById(R.id.questionTextView);
		questionTextView.setTextSize(30);
		Random rand = new Random();
		int  generatedNumber = rand.nextInt(1000) + 1;
		String questionString = "Is " + Integer.toString(generatedNumber) + " prime?";
		questionTextView.setText(questionString);
		this.currentNumber = generatedNumber;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_quiz);
		//get the username from previous activity and show a greeting message
		showGreeting(intent.getStringExtra(MainActivity.USER_NAME));
        //generate the question
		showFirstQuestion();
    }


	//called when yes/no is pressed
	public void checkResult(View view)
	{
		((Button) findViewById(R.id.button_next)).setEnabled(false);
		((Button) findViewById(R.id.button_yes)).setEnabled(false);
		((Button) findViewById(R.id.button_no)).setEnabled(false);
		((Button) findViewById(R.id.button_hint)).setEnabled(false);
		((Button) findViewById(R.id.button_cheat)).setEnabled(false);
		Context context = getApplicationContext();
		int answer = Integer.parseInt(view.getTag().toString());
		if (answer == isPrime(this.currentNumber))
		{
			CharSequence text = "Your answer is correct!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					nextQuestion((View) findViewById(R.id.button_next));
				}
			}, 2000);
		}
		else
		{
			CharSequence text = "Oops! Your answer is wrong!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					nextQuestion((View) findViewById(R.id.button_next));
				}
			}, 2000);

		}
	}

	/** Called when the user clicks the Start button */
	public void showHint(View view) {
		Button button_hint = ((Button) findViewById(R.id.button_hint));
		button_hint.setEnabled(false);
		button_hint.setText("Hint Used");
		if ( this.hintScene == 0 )
		{
			this.hintScene = 1;
			Intent intent = new Intent(this, HintActivity.class);
			intent.putExtra("CURRENT_NUMBER", this.currentNumber);
			startActivity(intent);
		}

	}

	/** Called when the user clicks the Start button */
	public void showCheat(View view) {
		Button button_cheat = ((Button) findViewById(R.id.button_cheat));
		button_cheat.setEnabled(false);
		button_cheat.setText("Cheat Used");
		if ( this.cheatScene == 0 )
		{
			this.cheatScene = 1;
			Intent intent = new Intent(this, CheatActivity.class);
			if ( isPrime(this.currentNumber) == 1 )
			{
				intent.putExtra("IS_PRIME", "prime.");
			}
			else
			{
				intent.putExtra("IS_PRIME", "not prime.");
			}
			startActivity(intent);
		}

	}


}
