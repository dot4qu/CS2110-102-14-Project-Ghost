package edu.virginia.cs2110.group14;


import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

public class Ghost {

	private int x;
	private int y;
	private ImageView ghost;
	private MainActivity game;
	private Rect ghostBounds;

	public Ghost(ImageView ghostImage) {
		ghost = ghostImage;
		ghostBounds = new Rect();
		//Point pt = game.randomPointGenerator();
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

	public ImageView getGhostImage() {
		return this.ghost;
	}
}
