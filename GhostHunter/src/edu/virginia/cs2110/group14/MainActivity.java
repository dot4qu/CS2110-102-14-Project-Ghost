package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView image = (ImageView) findViewById(R.id.ghostbuster);
		Button up = (Button) findViewById(R.id.button1);
		up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation anup = new TranslateAnimation(0,0,0,-500);
				anup.setDuration(1000);
				anup.setFillAfter(true);
				
				image.startAnimation(anup);
			}
		});
		Button down = (Button) findViewById(R.id.button4);
		down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation andown = new TranslateAnimation(0,0,0,500);
				andown.setDuration(1000);
				andown.setFillAfter(true);
				
				image.startAnimation(andown);
			}
		});
		Button right = (Button) findViewById(R.id.button2);
		right.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation anright = new TranslateAnimation(0,500,0,0);
				anright.setDuration(1000);
				anright.setFillAfter(true);
				
				image.startAnimation(anright);
			}
		});
		Button left = (Button) findViewById(R.id.button3);
		left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation anleft = new TranslateAnimation(0,-500,0,0);
				anleft.setDuration(1000);
				anleft.setFillAfter(true);
				
				image.startAnimation(anleft);
			}
		});
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
