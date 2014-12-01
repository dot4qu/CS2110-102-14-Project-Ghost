package edu.virginia.cs2110.group14;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private final int NUMGHOSTS_EASY = 1;
	private final int NUMGHOSTS_MEDIUM = 7;
	private final int NUMGHOSTS_HARD = 10;

	private Handler frame = new Handler();
	private ImageView ghostBuster;
	private Runnable r;
	private ArrayList<Ghost> ghostList;
	private int initialNumGhosts;
	private boolean gameOn;
	private int lives;
	private RelativeLayout screenLayout;
	private Point screenDimensions;
	
	private ImageView star1;
	private ImageView star2;
	private ImageView star3;
	
	//Soundpool for sound effects
	private SoundPool soundpool;
	private int warning = 0;
	 
	
	 
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Sound effects
		soundpool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		warning = soundpool.load(this, R.raw.warning , 1);

		// Music
		MediaPlayer gameMusic = MediaPlayer.create(MainActivity.this, R.raw.gamesound);
		gameMusic.start();

		// initializes buttons
		runButtons();
		
		// sets up ghostbuster image
		ghostBuster = (ImageView) findViewById(R.id.ghostbuster);
		Log.d("buster", "width: " + ghostBuster.getWidth() + " height: " + ghostBuster.getHeight());
		Log.d("buster", "top: " + ghostBuster.getX() + " bottom: " + ghostBuster.getY());

		
		//set up stars
		setUpStars();

		// set up star
		
		Star star1=new Star((ImageView)findViewById(R.id.star), 50, 50);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_center);
        star1.getStarImage().startAnimation(animation);

		// checks the difficulty level and sets the number of ghosts accordingly
		if (getDifficultyLevel() == 0)
			initialNumGhosts = NUMGHOSTS_EASY;
		else if (getDifficultyLevel() == 1)
			initialNumGhosts = NUMGHOSTS_MEDIUM;
		else if (getDifficultyLevel() == 2)
			initialNumGhosts = NUMGHOSTS_HARD;

		// creates all of the ghosts
		ghostList = new ArrayList<Ghost>();
		for (int i = 0; i < initialNumGhosts; i++) {
			ghostList.add(makeGhost());
		}
		
		gameOn = true;
		lives = 3;
		runOverall();

	}

	public void runOverall() {
		r = new Runnable() {
			public void run() {
				for (int i = 0; i < ghostList.size(); i++) {
					ghostList.get(i).move();
					collisionResponse(ghostList.get(i));
			        
					if (!gameOn) {
						//end activity and stop thread
						Intent intent = new Intent(MainActivity.this, GameOver.class);
						startActivity(intent);
					}
				}
				frame.postDelayed(r, 20);
			}
		};
			r.run();
	}

	public Point randomPointGenerator() {
		Point screen = getScreenSize();
		int x = (int) Math.abs(Math.random() * (screen.x - 100));
		int y = (int) Math.abs(Math.random() * (screen.y - 250));
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

	public void collisionResponse(Ghost g) {
		screenDimensions = getScreenSize();
		//keeps the ghost within the screen
		if (g.getX() + g.getGhostImage().getWidth() > screenDimensions.x - 10 || g.getX() < 10)
			g.changeVelocity(-g.getXVelocity(), g.getYVelocity());
		if (g.getY() > screenDimensions.y - 275 || g.getY() < 10)
			g.changeVelocity(g.getXVelocity(), -g.getYVelocity());
		
		String action = CollisionHandler.busterGhostCollisions(g, ghostBuster);
		if (action.compareTo("ghost on side") == 0) {
			g.changeVelocity(-g.getXVelocity(), g.getYVelocity()); //bounces ghost off
			// sound warning that ghost is close
			soundpool.play(warning, 1, 1, 0, 0, 1);
			soundpool.autoPause();
		}
		if (action.compareTo("ghost on bottom=kill ghost") == 0) {
			screenLayout.removeView(g.getGhostImage());
			ghostList.remove(g);	//deletes the ghost
			g.removeGhostImage();
			g = null;
		}
		if (action.compareTo("ghost on top=kill buster") == 0) {	//make buster lose a life or game over
			if (lives == 1)
				gameOn = false;
			else {
				Log.d("death", "dead");
				ghostBuster.setX(screenDimensions.x / 2);
				ghostBuster.setY(screenDimensions.y / 2);
				lives--;
			}
			
		}
	}

	// call to make a new ghost object and put it on the screen at a random
	// point
	public Ghost makeGhost() {
		ImageView ghostIMG = new ImageView(this);
		ghostIMG.setImageResource(R.drawable.ghost);
		Point pt = this.randomPointGenerator();
		screenLayout.addView(ghostIMG);
		return new Ghost(ghostIMG, pt.x, pt.y);
	}
	
	public void setUpStars() {
		// set up star
				ImageView star1 = new ImageView(this);
				star1.setImageResource(R.drawable.star);
		        ImageView star2 = new ImageView(this);
				star2.setImageResource(R.drawable.star);
		        ImageView star3 = new ImageView(this);
				star3.setImageResource(R.drawable.star);
				
				Animation spin = AnimationUtils.loadAnimation(this, R.anim.rotate_center);
				//star1.startAnimation(spin);
				//star2.startAnimation(spin);
				//star3.startAnimation(spin);
		        
		        screenLayout = (RelativeLayout) findViewById(R.id.screen);
		        
		        screenLayout.addView(star1);
		        screenLayout.addView(star2);
		        screenLayout.addView(star3);
		        star1.setY(1115);
		        star1.setX(10);
		        star2.setY(1115);
		        star2.setX(65);
		        star3.setY(1115);
		        star3.setX(120);
	}

	public void runButtons() {
		// beginning of UP button implementation
		Button up = (Button) findViewById(R.id.up_button);
		up.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.topMargin -= 10;
				ghostBuster.setLayoutParams(mParams);
				Log.d("buster", "postion x: " + ghostBuster.getX() + " position y: " + ghostBuster.getY());
				return true;
				}
				return false;
			}
		});

		// beginning of DOWN button implementation
		Button down = (Button) findViewById(R.id.down_button);
		down.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.topMargin += 10;
				ghostBuster.setLayoutParams(mParams);
				Log.d("buster", "postion x: " + ghostBuster.getX() + " position y: " + ghostBuster.getY());
				return true;
				}
				return false;
			}
		});

		// beginning of RIGHT button implementation
		Button right = (Button) findViewById(R.id.right_button);
		right.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.leftMargin += 10;
				ghostBuster.setLayoutParams(mParams);
				Log.d("buster", "postion x: " + ghostBuster.getX() + " position y: " + ghostBuster.getY());
				return true;
				}
				return false;
			}
		});

		// beginning of LEFT button implementation
		Button left = (Button) findViewById(R.id.left_button);
		left.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
				RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) ghostBuster
						.getLayoutParams();
				mParams.leftMargin -= 10;
				ghostBuster.setLayoutParams(mParams);
				Log.d("buster", "postion x: " + ghostBuster.getX() + " position y: " + ghostBuster.getY());
				return true;
				}
				return false;
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
