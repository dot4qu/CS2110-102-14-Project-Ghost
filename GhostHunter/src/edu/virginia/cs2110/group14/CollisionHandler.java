package edu.virginia.cs2110.group14;

import android.graphics.Rect;
import android.widget.ImageView;

public class CollisionHandler {

 private Rect ghostBox;
 private Rect busterBox;
 
 public CollisionHandler(Ghost ghost, ImageView ghostBuster) {
 ghostBox=new Rect();
 ghost.getGhostImage().getDrawingRect(ghostBox);
 ghostBuster.getDrawingRect(busterBox);
 
}
 
 public static void busterGhostCollision (Rect rect1, Rect rect2) {
	 
 }
 
 
}