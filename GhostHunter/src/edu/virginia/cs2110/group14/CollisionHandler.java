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
	
	

	public static String busterGhostCollisions(Ghost ghost, ImageView ghostBuster) {

		Rect intersection = new Rect();
		ImageView ghostImage = ghost.getGhostImage();
		
		//makes new rectangle over current position every frame iteration
		Rect ghostBox = new Rect(ghost.getX(), ghost.getY(), ghost.getX() + ghostImage.getWidth(), ghost.getY() + ghostImage.getHeight());
		Rect busterBox = new Rect(ghostBuster.getLeft(), ghostBuster.getTop(), ghostBuster.getRight(), ghostBuster.getBottom());
		
		//debugging
//		Log.d("rects", "ghost -- getx: " + ghost.getX() + " x+width: " + ghost.getX() + ghostImage.getWidth());
//		Log.d("rects", "Buster -- centery: " + busterBox.centerY() + " top: " + busterBox.top + " bottom: " + busterBox.bottom);

		//if they intersect, makes a rectangle with the dimensions of how they intersected
		if (ghostBox.intersect(busterBox)) {
			//ghost is to the left
			if (ghostBox.centerX() < busterBox.centerX()) {
				//ghost is higher
				if (ghostBox.centerY() < busterBox.centerY()) {
					intersection = new Rect(busterBox.left, busterBox.top, ghostBox.right, ghostBox.bottom);
				}
					//buster is higher
				else if (ghostBox.centerY() > busterBox.centerY()) {
					intersection = new Rect(busterBox.left, ghostBox.top, ghostBox.right, busterBox.bottom); 
				}	
			}
			//ghost is on right
			else if (ghostBox.centerX() > busterBox.centerX()) {
				//ghost is higher
				if (ghostBox.centerY() < busterBox.centerY()) {
					intersection = new Rect(ghostBox.left, busterBox.top, busterBox.right, ghostBox.bottom);
					//Log.d("collision", "missing");
				}
				//buster is higher
				else if (ghostBox.centerY() > busterBox.centerY()) {
					intersection = new Rect(ghostBox.left, ghostBox.top, busterBox.right, busterBox.bottom);
					//Log.d("collision", "expected");
				}
			}
		}
		
			//then uses the 'intersection' rectangle's dimensions to determine who is above whom
			//if the width of the intersection rectangle is bigger than the height, then it's a top bottom collision
			//if the height is bigger than the width, its a sideways collision
			
			//vertical collision
			if (intersection.width() > intersection.height()) {
				//Log.d("collision", "vertical");
				if (busterBox.centerY() < ghostBox.centerY()) {
					return "ghost on bottom=kill ghost";
				}
				else if (busterBox.centerY() > ghostBox.centerY()) {
					return "ghost on top=kill buster";
				}
			}
			//sideways collision
			else if (intersection.height() >intersection.width()) {
				if (ghostBox.centerX() < busterBox.centerX()) {  	//ghost is on left
				ghost.setXCoord(ghost.getX() - intersection.width());
				}
				else if (ghostBox.centerX() > busterBox.centerX()) {	//ghost is on the right
					ghost.setXCoord(ghost.getX() + intersection.width());
				}
				return "ghost on side";
			}
			
			
			
			
			/**
			 *  if (ghostBox.centerX() < busterBox.centerX() && ghostBox.bottom >=
			 busterBox.top && busterBox.bottom >= ghostBox.top) {
			 return "ghost on left";
			 }
			 if (ghostBox.centerX()>busterBox.centerX() && ghostBox.bottom >=
			 busterBox.top && busterBox.bottom >= ghostBox.top) {
			 return "ghost on right";
			 }
			 if (ghostBox.centerY() > busterBox.centerY() && busterBox.left < ghostBox.right && busterBox.right > ghostBox.left) {
			 return "ghost on bottom=kill ghost";
			 }
			 if (ghostBox.centerY() < busterBox.centerY()) {
			 return "ghost on top=kill buster";
			 } 
			 **/

		
		return "no collision";
	}

}

// to call method: busterGhostCollision(ghost, hunter)
// returns a string, and then use compare to to handle the collison
