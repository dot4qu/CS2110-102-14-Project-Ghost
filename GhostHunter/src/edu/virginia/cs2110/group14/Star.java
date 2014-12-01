package edu.virginia.cs2110.group14;


import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Star {

	public int x;
	public int y;
	private ImageView star;

	

	public Star (ImageView starImage, int x, int y) {
		star = starImage;
		star.setX(x);
		star.setY(y);

		Log.d("Star point", "Star  -- X: " + x + ", Y: " + y);
		
	}
	

	public ImageView getStarImage() {
		return this.star;
	}
	
	


//	public void startAnimation(Animation animation) {
	//	Animation animation = AnimationUtils.loadAnimation(null, R.anim.rotate_center);
	//}
	
}


//	// set up star
//ImageView star=(ImageView)findViewById(R.id.star); 
//Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_center);
//star.startAnimation(animation);