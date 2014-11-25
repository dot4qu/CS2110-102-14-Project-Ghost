package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class DifficultyScreen extends Activity {

	private Button easyButton;
	private Button mediumButton;
	private Button hardButton;
	private int difficultyLevel;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty_screen);
		
		easyButton = (Button) findViewById(R.id.easy_button);
		mediumButton = (Button) findViewById(R.id.medium_button);
		hardButton = (Button) findViewById(R.id.hard_button);
		
		final Intent mainIntent = new Intent(DifficultyScreen.this, MainActivity.class);
		
		easyButton.setOnTouchListener(new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent e) {
			if (e.getAction() == MotionEvent.ACTION_UP) {
				difficultyLevel = 0;
				mainIntent.putExtra("DIFFICULTY", difficultyLevel);
				startActivity(mainIntent);
				return true;
			}
			return false;
		}
		});
		
		mediumButton.setOnTouchListener(new View.OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_UP) {
					difficultyLevel = 1;
					mainIntent.putExtra("DIFFICULTY", difficultyLevel);
					startActivity(mainIntent);
					return true;
				}
				return false;
			}
		});
		
		hardButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_UP) {
					difficultyLevel = 2;
					mainIntent.putExtra("DIFFICULTY", difficultyLevel);
					startActivity(mainIntent);
					return true;
				}
				return false;
			}
		});
	}
}
