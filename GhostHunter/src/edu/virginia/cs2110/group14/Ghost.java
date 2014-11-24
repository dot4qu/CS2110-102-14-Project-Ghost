package edu.virginia.cs2110.group14;


import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

public class Ghost {

	private int x;
	private int y;
	private ImageView ghost;
	private MainActivity game;

	public Ghost(ImageView ghostImage) {
		ghost = ghostImage;
		Point pt = game.randomPointGenerator();
		ghost.setX(x);
		ghost.setY(y);
		Log.d("point", "x: " + pt.x + ", y: " + pt.y);
		x++;
		y++;
	}

}
