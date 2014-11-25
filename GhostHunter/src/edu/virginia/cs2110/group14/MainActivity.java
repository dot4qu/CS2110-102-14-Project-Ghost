package edu.virginia.cs2110.group14;

import java.util.ArrayList;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private final int NUMGHOSTS_EASY = 4;
	private final int NUMGHOSTS_MEDIUM = 7;
	private final int NUMGHOSTS_HARD = 10;

	private Handler frame = new Handler();
	private ImageView ghostBuster;
	private Runnable r;
	private Ghost ghost1;
	private Ghost ghost2;
	private RelativeLayout screenLayout;
	private ArrayList<Ghost> ghostList;
	private int numGhosts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		screenLayout = (RelativeLayout) findViewById(R.id.screen);

		// Music
		MediaPlayer gameMusic = MediaPlayer.create(MainActivity.this,
				R.raw.gamesound);
		gameMusic.start();

		// initializes buttons
		runButtons();

		// sets up ghostbuster image
		ghostBuster = (ImageView) findViewById(R.id.ghostbuster);

		// checks the difficulty level and sets the number of ghosts accordingly
		if (getDifficultyLevel() == 0)
			numGhosts = NUMGHOSTS_EASY;
		else if (getDifficultyLevel() == 1)
			numGhosts = NUMGHOSTS_MEDIUM;
		else if (getDifficultyLevel() == 3)
			numGhosts = NUMGHOSTS_HARD;

		// creates all of the ghosts
		ghostList = new ArrayList<Ghost>();
		for (int i = 0; i < numGhosts; i++) {
			ghostList.add(makeGhost());
		}

		runOverall();

	}

	public void runOverall() {
		r = new Runnable() {
			public void run() {
				// ghost1.move();
				frame.postDelayed(r, 1000);
			}
		};
		r.run();
	}

	public Point randomPointGenerator() {
		// RelativeLayout screenLayout = (RelativeLayout)
		// this.findViewById(R.id.screen);
		Point screen = getScreenSize();
		int x = (int) (Math.random() * Math.abs(screen.x - 100));
		int y = (int) (Math.random() * Math.abs(screen.y - 250));
		Point p = new Point(x, y);
		return p;
	}

	// returns the dimensions of the screen as a point
	// x value is width, y value is height
	public Point getScreenSize() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	public void CollisionResponse(String str) {
		String action = "";// CollisionHandler.busterGhostCollisions(ghost1,
							// ghostBuster);
		if (action.compareTo("ghost on left") == 0
				|| action.compareTo("ghost on right") == 0) {
			// sound warning that ghost is close
		}
		if (action.compareTo("ghost on bottom=kill ghost") == 0) {
			// make ghost disappear
		}
		if (action.compareTo("ghost on top=kill buster") == 0) {
			// make buster lose a life
		}
	}

	// call to make a new ghost object and put it on the screen at a random
	// point
	public Ghost makeGhost() {
		ImageView ghostIMG = new ImageView(this);
		ghostIMG.setImageResource(R.drawable.ghost);
		Point pt = this.randomPointGenerator();
		RelativeLayout screenLayout = (RelativeLayout) findViewById(R.id.screen);
		screenLayout.addView(ghostIMG);
		Log.d("Ghost point", "GHOST COORDS -- X: " + pt.x + ", Y: " + pt.y);

		return new Ghost(ghostIMG, pt.x, pt.y);
	}

	public void runButtons() {
		// beginning of UP button implementation
		Button up = (Button) findViewById(R.id.up_button);
		up.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.topMargin -= 50;
				ghostBuster.setLayoutParams(mParams);

			}
		});

		// beginning of DOWN button implementation
		Button down = (Button) findViewById(R.id.down_button);
		down.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.topMargin += 50;
				ghostBuster.setLayoutParams(mParams);
			}
		});

		// beginning of RIGHT button implementation
		Button right = (Button) findViewById(R.id.right_button);
		right.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.leftMargin += 50;
				ghostBuster.setLayoutParams(mParams);
			}
		});

		// beginning of LEFT button implementation
		Button left = (Button) findViewById(R.id.left_button);
		left.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.leftMargin -= 50;
				ghostBuster.setLayoutParams(mParams);
			}
		});
	}

	//gets the difficulty level from the difficulty screen
	public int getDifficultyLevel() {
		Intent mainIntent = getIntent();
		return mainIntent.getIntExtra("DIFFICULTY", -1);
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
