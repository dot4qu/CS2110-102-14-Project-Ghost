package edu.virginia.cs2110.group14;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* this is the view that sits on top of the MainActivity layout. The layout 
 * serves to be there for the background and to be the activity running. This
 * view is what handles all of the game animation / redrawing
 */


public class GameScreen extends View {
	
	private Paint paint;
	
	//maintains ghost position
	private Rect ghost1Bounds = new Rect(0,0,0,0);
	private Point ghost1;
	
	//holds ghost picture
	private Bitmap ghost1bm = null;
	
	//used for ghost position
	int x, y;
	
	//ghost position getters and setters
	public void setGhost1(Point p) {
		ghost1 = p;
	}
	
	public Point getGhost1() {
		return ghost1;
	}
	
	public int getGhost1Width() {
		return ghost1Bounds.width();
	}
	
	public int getGhost1Height() {
		return ghost1Bounds.height();
	}
	
	
	
	//default constructor, needed because View is being extended
	public GameScreen(Context context, AttributeSet aSet) {
		super(context, aSet);
		
		//initializes the paint that will be used to draw everything when in the onDraw method
		paint = new Paint();
		
		//setting the ghost sprite at an arbitrary location for testing
		ghost1 = new Point(1,1);
		ghost1bm = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
		//updates sprite bounds
		ghost1Bounds = new Rect(0, 0, ghost1bm.getWidth(), ghost1bm.getHeight());
	}
	
	
	
	
	//this is the method that draws and redraws the screen rapidly
	//giving it the effect of animation
	@Override
	public void onDraw(Canvas canvas) {
		//to move and keep ghost on screen
		if (x<canvas.getWidth()){
			x += 3;
		} else {
			x = 0;
		}
		if (y<canvas.getHeight()) {
			y += 3;
		} else {
			y= 0;
		}
		
		canvas.drawBitmap(ghost1bm, x, y, new Paint());
		invalidate();
	}
	
	
}
