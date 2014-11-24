package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
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
	private ImageView ghost;
	private ImageView ghostBuster;
	private Runnable r;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Music
		MediaPlayer gameMusic = MediaPlayer.create(MainActivity.this, R.raw.gamesound);
		gameMusic.start();
		ghostBuster = (ImageView) findViewById(R.id.ghostbuster);
		ghost = (ImageView) findViewById(R.id.ghostID);
		Ghost ghost1 = new Ghost(ghost);
		runOverall();
		
		//beginning of UP button implementation
		Button up = (Button) findViewById(R.id.button1);
		up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                ghostBuster.getLayoutParams();
		                mParams.topMargin -= 50;
		                ghostBuster.setLayoutParams(mParams);

			}
		});
		
		//beginning of DOWN button implementation
		Button down = (Button) findViewById(R.id.button4);
		down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                ghostBuster.getLayoutParams();
		                mParams.topMargin += 50;
		                ghostBuster.setLayoutParams(mParams);
			}
		});
		
		//beginning of RIGHT button implementation
		Button right = (Button) findViewById(R.id.button2);
		right.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                ghostBuster.getLayoutParams();
		                mParams.leftMargin += 50;
		                ghostBuster.setLayoutParams(mParams);
			}
		});
		
		//beginning of LEFT button implementation
		Button left = (Button) findViewById(R.id.button3);
		left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) 
		                ghostBuster.getLayoutParams();
		                mParams.leftMargin -= 50;
		                ghostBuster.setLayoutParams(mParams);
			}
		});
		
		
		
	}
	
	public void runOverall() {
		Log.d("thread", "in thread");
			r = new Runnable() {
			public void run() {
				Log.d("thread", "in run");
				//ghost.setX(ghost1.getX()+1);
				//ghost.setY(ghost1.getY()+1);
				//ghost1.getGhostImage().invalidate();
				frame.postDelayed(r, 1000);
				
			}
		};
		r.run();
	}
	
	public Point randomPointGenerator() {
		int x = (int) Math.random() * findViewById(R.id.game_canvas).getWidth();
		Log.d("width", "width: " + findViewById(R.id.game_canvas).getWidth());
		int y = (int) Math.random() * findViewById(R.id.game_canvas).getHeight();
		Log.d("height", "Height: " + findViewById(R.id.game_canvas).getHeight());
		Point p = new Point(x, y);
		Log.d("Point", "x: "+ p.x + ", y: " + p.y);
		return p;
	}
	
	
	
	
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
