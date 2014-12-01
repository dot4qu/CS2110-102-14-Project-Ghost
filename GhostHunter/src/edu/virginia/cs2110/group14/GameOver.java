package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GameOver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		
		Button restart = (Button) findViewById(R.id.play_again_button);
		restart.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Intent intent = new Intent(GameOver.this, DifficultyScreen.class);
					startActivity(intent);
					finish();
					return true;
				}
				else
					return false;
			}
		});
	}
}
