package edu.virginia.cs2110.group14;


import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

public class Ghost {

	public int x;
	public int y;
	private ImageView ghost;
	private Rect ghostBounds;
	private float xVeloc;
	private float yVeloc;
	

	public Ghost(ImageView ghostImage, int x, int y) {
		ghost = ghostImage;
		ghost.setX(x);
		ghost.setY(y);
		Log.d("Ghost point", "GHOST  -- X: " + x + ", Y: " + y);
		xVeloc = randomInitialVelocity();
		xVeloc = randomInitialVelocity();
	}
	
	public int getX() {
		return (int) ghost.getX();
	}
	
	public int getY() {
		return (int) ghost.getY();
	}
	
	public void setXCoord(int i) {
		ghost.setX(i);
	}
	
	public void setYCoord(int i) {
		ghost.setY(i);
	}

	public ImageView getGhostImage() {
		return this.ghost;
	}
	
	public float getXVelocity() {
		return this.xVeloc;
	}
	
	public float getYVelocity() {
		return this.yVeloc;
	}
	
	public void move() {
		ghost.setX(ghost.getX() + xVeloc);
		ghost.setY(ghost.getY() + yVeloc);
		ghost.invalidate();
	}
	
	public void changeVelocity(float xv, float yv) {
		xVeloc = xv;
		yVeloc = yv;
	}
	
	public float randomInitialVelocity() {
		float pos = (float) Math.random() * 6;
		return 3 - pos;
	}
}
