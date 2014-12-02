package edu.virginia.cs2110.group14;


import android.util.Log;
import android.widget.ImageView;

public class Ghost {

	public int x;
	public int y;
	private ImageView ghost;
	private float xVeloc;
	private float yVeloc;
	

	public Ghost(ImageView ghostImage, int x, int y) {
		ghost = ghostImage;
		ghost.setX(x);
		ghost.setY(y);
		xVeloc = randomInitialVelocity();
		yVeloc = randomInitialVelocity();
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
	
	public void removeGhostImage() {
		this.ghost = null;
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
		if (pos > 1 && pos <=3)
			pos = 1;
		else if (pos > 3 && pos < 5)
			pos = 5;
		return 3 - pos;
	}
}
