package edu.virginia.cs2110.group14;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.ImageView;

public class CollisionHandler {

 
 public static String busterGhostCollisions (ImageView ghost, ImageView ghostBuster) {
	 
 Rect ghostBox=new Rect();
 Rect busterBox=new Rect();
 Rect bottomBound= new Rect();
 Rect topBound=new Rect();
 Rect leftBound=new Rect();
 Rect rightBound=new Rect();
 
 ghost.getDrawingRect(ghostBox);
 ghostBuster.getDrawingRect(busterBox);
 
 //handles ghost on buster collision
 if (ghostBox.intersect(busterBox)) {
	 if (ghostBox.centerX()<busterBox.centerX()) {
		 return "ghost on left";
	 } 
	 if (ghostBox.centerX()>busterBox.centerX()) {
		 return "ghost on right";
	 }
	 if (ghostBox.centerY()<busterBox.centerY()) {
		 return "ghost on bottom=kill ghost";
	 }
	 if (ghostBox.centerY()>busterBox.centerY()) {
		 return "ghost on top=kill buster";
	 }
	 
//handles ghost on wall collision
	 
	 
//handles buster on wall collision
	 
 }
return "no collision";
}
 
 

 
 
}

// to call method: busterGhostCollision(ghost, hunter)
//returns a string, and then use compare to to handle the collison
