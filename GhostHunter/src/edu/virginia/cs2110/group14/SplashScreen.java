package edu.virginia.cs2110.group14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ImageView;


public class SplashScreen extends Activity {

	private int SPLASHSCREEN_LENGTH = 5000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		
		new Handler().postDelayed(new Runnable() {
		@Override
		public void run() {
			Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
			startActivity(mainIntent);
			finish();
		}
		}, SPLASHSCREEN_LENGTH);
	}
}
