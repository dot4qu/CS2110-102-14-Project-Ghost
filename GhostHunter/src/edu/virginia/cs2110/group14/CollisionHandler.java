package edu.virginia.cs2110.group14;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class CollisionHandler {

	public static String busterGhostCollisions(Ghost ghost,
			ImageView ghostBuster) {
/*something's weird with the width and height of each image when it's getting it,
 * its remaining constant on the screen for some reason so it changes whenever one of them moves
 * You can check to see by watching the printout in logcat as its running and you move the guy
 * I'll fix it soon. The reason i took away the drawRects is because they werent drawing the right 
 * sized rectangles for each image, but they were both just sitting in the upper left hand corner and not moving with the image
 */
ImageView ghostImage = ghost.getGhostImage();
		
		//makes new rectangle over current position every frame iteration
		Rect ghostBox = new Rect(ghost.getX(), ghost.getY(), ghost.getX() + ghostImage.getWidth(), ghost.getY() + ghostImage.getHeight());
		Rect busterBox = new Rect(ghostBuster.getLeft(), ghostBuster.getTop(), ghostBuster.getRight(), ghostBuster.getBottom());
		
		//debugging
		Log.d("rects", "ghost -- x: " + ghost.getX() + " y: " + ghost.getY());
		Log.d("rects", "Buster -- x: " + busterBox.left + " y: " + busterBox.top);

		// handles ghost on buster collision
		if (ghostBox.intersect(busterBox)) {

			 if (ghostBox.centerX()<busterBox.centerX() && ghostBox.bottom >=
			 busterBox.top && busterBox.bottom >= ghostBox.top) {
			 return "ghost on left";
			 }
			 if (ghostBox.centerX()>busterBox.centerX() && ghostBox.bottom >=
			 busterBox.top && busterBox.bottom >= ghostBox.top) {
			 return "ghost on right";
			 }
			 if (ghostBox.centerY()<busterBox.centerY()) {
			 return "ghost on bottom=kill ghost";
			 }
			 if (ghostBox.centerY()>busterBox.centerY()) {
			 return "ghost on top=kill buster";
			 }

			// handles ghost on wall collision


			// handles buster on wall collision

		}
		return "no collision";
	}

}

// to call method: busterGhostCollision(ghost, hunter)
// returns a string, and then use compare to to handle the collison
