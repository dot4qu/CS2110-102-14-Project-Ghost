package edu.virginia.cs2110.group14;


import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

public class Ghost {

	private int x;
	private int y;
	private ImageView ghost;
	private Rect ghostBounds;
	private int xVeloc;
	private int yVeloc;
	

	public Ghost(ImageView ghostImage, int x, int y) {
		ghost = ghostImage;
		ghost.setX(x);
		ghost.setY(y);
		//Log.d("point", "x: " + pt.x + ", y: " + pt.y);
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
		return ghost;
	}
	
	public void move() {
		ghost.setX(ghost.getX() + 1);
		ghost.setY(ghost.getY() + 1);
		ghost.invalidate();
	}
	
	public void changeVelocity(int xv, int yv) {
		ghost.setX(ghost.getX() + xv);
		ghost.setY(ghost.getY() + yv);
	}
}
