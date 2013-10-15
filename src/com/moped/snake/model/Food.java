package com.moped.snake.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.moped.snake.Actions;
import com.moped.snake.Tiles;

public class Food implements Game {
	
	private Point position;
	private float width, height;
	private Paint paint;

	public Food() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLUE);
	}
	
	public void draw(Canvas canvas) {
		float x = position.x*width;
		float y = position.y*height;
		canvas.drawRect(x, y, x+width, y+height, paint);
	}

	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public void setPosition(Point point) {
		this.position = point;
	}

	public char getTile() {
		return Tiles.FOOD;
	}

	public Point getPosition() {
		return position;
	}

	public void updatePositions() {
		// we do not do anything because the food cannot move
	}

	public int updateLogic(char tile) {
		if(tile == Tiles.SNAKE)
			return Actions.FOOD_EATEN;
		return Actions.NOTHING_HAPPEN;
	}

	public boolean contains(Point point) {
		return (point.x == position.x && point.y == position.y);
	}

	public void onTouch(float x, float y) {
		// we do not do anything because the food cannot move
	}

	public boolean crash() {
		// TODO NO CHILDREN, NOT USED
		return false;
	}


}
