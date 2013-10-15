package com.moped.snake.model;

import android.graphics.Canvas;
import android.graphics.Point;

/** Implement this interface to add different objects to the game */
public interface Game {
	
	/** Obtain the object ID */
	public char getTile();
	
	/** Obtain the object position */
	public Point getPosition();
	
	/** The responsible to drawing and giving a shape to the object */
	public void draw(Canvas canvas);
	
	/** Called every frame to add movement to the game */
	public void updatePositions();
	
	/** Called every frame after the positions are updated to check the physics */
	public int updateLogic(char tile);
	
	/** Set the dimensions of the object */
	public void setDimensions(float width, float height);
	
	/** Set the position of the object */
	public void setPosition(Point point);
	
	/** Checks whether this point is inside the object or not */
	public boolean contains(Point point);
	
	/** Checks whether this object crashes with itself (via his children) or not */
	public boolean crash();
	
	/** Called when the user touches the screen */
	public void onTouch(float x, float y);
	
}
