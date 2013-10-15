package com.moped.snake.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Point;
import com.moped.snake.Actions;
import com.moped.snake.Tiles;

public class Snake implements Game {
	
	private List<SnakeBodyItem> body = new ArrayList<SnakeBodyItem>();
	
	/** The snake direction*/
	private int dx = 1, dy = 0;

	int numEaten = 0;

	public void draw(Canvas canvas) {
		for(SnakeBodyItem item: body)
			item.draw(canvas);
	}

	public void setDimensions(float width, float height) {
		for(SnakeBodyItem item: body)
			item.setDimensions(width, height);
	}

	public void setPosition(Point position) {
		body.add(new SnakeBodyItem(position));
	}

	public char getTile() {
		return Tiles.SNAKE;
	}

	public Point getPosition() {
		return body.get(0).getPosition();
	}

	public void updatePositions() {
		int x = body.get(0).getPosition().x;
		int y = body.get(0).getPosition().y;
		body.add(0, new SnakeBodyItem(new Point(x + dx, y + dy)));
		
	}

	public int updateLogic(char tile) {
		switch(tile) {
			
		case Tiles.SNAKE:
			return Actions.SNAKE_CRASH;
		case Tiles.WALL: 
			return Actions.SNAKE_CRASH;
		case Tiles.EMPTY:
			body.remove(body.size()-1);
			return Actions.NOTHING_HAPPEN;
		case Tiles.FOOD:
		default:
			return Actions.NOTHING_HAPPEN;
		
		}
	}

	public boolean contains(Point point) {
		for(SnakeBodyItem item: body)
			if(item.contains(point))
				return true;
		return false;
	}
	
	public void onTouch(float x, float y) {
		final int px = getPosition().x;
		final int py = getPosition().y;
		final boolean movingHoritzontally = dy == 0;
		if(movingHoritzontally) {
			if(y > py)
				dy = +1;
			else
				dy = -1;
			dx = 0;
		} else {
			if(x > px)
				dx = +1;
			else
				dx = -1;
			dy = 0;
		}
			
	}

	public boolean crash() {
		for(int i = 0; i < body.size(); i++)
			for(int j = i+1; j < body.size(); j++)
				if(body.get(i).contains(body.get(j).getPosition()))
					return true;
		return false;
	}
}
