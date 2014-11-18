package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import edu.virginia.cs2110.group14.*;

public class MainActivity extends Activity {

	private Handler frame = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Music
		MediaPlayer gameMusic = MediaPlayer.create(MainActivity.this, R.raw.gamesound);
		gameMusic.start();
		final ImageView image = (ImageView) findViewById(R.id.ghostbuster);
		
		//beginning of UP button implementation
		Button up = (Button) findViewById(R.id.button1);
		up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                image.getLayoutParams();
		                mParams.topMargin -= 50;
		                image.setLayoutParams(mParams);
		                
		                // CHANGE TEST
			}
		});
		
		//beginning of DOWN button implementation
		Button down = (Button) findViewById(R.id.button4);
		down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                image.getLayoutParams();
		                mParams.topMargin += 50;
		                image.setLayoutParams(mParams);
			}
		});
		
		//beginning of RIGHT button implementation
		Button right = (Button) findViewById(R.id.button2);
		right.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                image.getLayoutParams();
		                mParams.leftMargin += 50;
		                image.setLayoutParams(mParams);
			}
		});
		
		//beginning of LEFT button implementation
		Button left = (Button) findViewById(R.id.button3);
		left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                image.getLayoutParams();
		                mParams.leftMargin -= 50;
		                image.setLayoutParams(mParams);
			}
		});
	}

	
	
	private Runnable updateScreen = new Runnable() {
		
		//MAKE ANY SCREEN UPDATES THAT NEED TO HAPPEN EACH FRAME WITHIN run()
		//this means things like redrawing sprites in a different place to make
		// move
		@Override
		public void run() {
			frame.removeCallbacks(updateScreen);
			//this resets the canvas, re-calling onDraw
			((GameScreen)findViewById(R.id.game_canvas)).invalidate();
			
			frame.postDelayed(updateScreen, 1000);
		}
	};
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
