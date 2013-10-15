package com.moped.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.moped.snake.Actions;
import com.moped.snake.Tiles;

/** Contains all the objects of the game */
public class World implements Game {
	
	/** The playing field */
	private final char[][] field; 
	/** Size of the playing field */
    private final int numRows, numColumns;
    /** Dimensions of the field */
    private float width, height;
    /** The guy who decides the color of the wall */
    private final Paint paint;
    
    /** Number of food eaten */
    private int score = 0;
	
    /** The random position generators to place the objects in the field */
    private Random xRandom, yRandom;
    
    private OnFinishGameListener onFinishGameListener = null;
    
	private List<Game> gameObjects = new ArrayList<Game>(); 
	
	public World(String source) {
		String[] rows = source.split("\n");
		numRows = rows[0].length();
		numColumns = rows.length;
		
		field = new char[numRows][numColumns];
		
		for(int i = 0; i < numColumns; i++)
			for(int j = 0; j < numRows; j++)
				field[j][i] = rows[i].charAt(j);
    	
		
    	paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	paint.setColor(Color.BLACK);
    	
    	xRandom = new Random();
    	yRandom = new Random();
	}
	
	public void addGameObject(Game game) {
		gameObjects.add(game);
	}

	public void draw(Canvas canvas) {
		drawField(canvas);
		for(Game game: gameObjects)
			game.draw(canvas);
	}

	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
		for(Game game: gameObjects)
			game.setDimensions(width/numRows, height/numColumns);
	}
	
	public void drawField(Canvas canvas) {
		float cellWidht = width/numRows;
		float cellHeight = height/numColumns;
		
		float x = 0;
		float y = 0;
		for(int i = 0; i < numColumns; i++) {
			for(int j = 0; j < numRows; j++) {
				if(field[j][i] == Tiles.WALL)
					canvas.drawRect(x, y, x+cellWidht, y+cellHeight, paint);
				x += cellWidht;
			}
			x = 0;
			y += cellHeight;
		}
	}

	public void setPosition(Point p) {
		int x, y;
		boolean fullCell = true;
		for(int i = 0; i < gameObjects.size(); i++) {
			do {
				x = xRandom.nextInt(numRows-1);
				y = yRandom.nextInt(numColumns-1);
				if(field[x][y] == Tiles.EMPTY) {
					fullCell = false;
					for(int j = 0; j < i; j++)
						if(gameObjects.get(j).contains(new Point(x, y)))
							fullCell = true;
					
				}
				
			} while(fullCell);
			
			gameObjects.get(i).setPosition(new Point(x, y));
			fullCell = true;
		}
	}

	public char getTile() {
		return Tiles.WALL;
	}

	public Point getPosition() {
		return new Point();
	}

	public void updatePositions() {
		for(Game game: gameObjects)
			game.updatePositions();
		updateLogic(' ');
	}
	
	private void manageLogic(int pointerToGameObj, int action) {
		
		if(action == Actions.FOOD_EATEN) {
			score++;
			updatePosition(pointerToGameObj);
		} else if(action == Actions.SNAKE_CRASH) {
			if(onFinishGameListener != null) onFinishGameListener.onFinishGame(score);
		}
	}
	
	private void updatePosition(int pointerToGameObj) {
		int x, y;
		boolean fullCell = true;
		do {
			x = xRandom.nextInt(numRows-1);
			y = yRandom.nextInt(numColumns-1);
			if(field[x][y] == Tiles.EMPTY) {
				fullCell = false;
				for(int j = 0; j < gameObjects.size(); j++)
					if(gameObjects.get(j).contains(new Point(x, y)))
						fullCell = true;
			}
			
		} while(fullCell);
		
		gameObjects.get(pointerToGameObj).setPosition(new Point(x, y));
		fullCell = true;
	}

	public int updateLogic(char tile) {
		for(int i = gameObjects.size()-1; i >= 0; i-- ) {
			Point p = gameObjects.get(i).getPosition();
			char cell = Tiles.EMPTY;
			
			if(contains(p)) { // crashed with parent (field)
				cell = field[p.x][p.y];
			} else if(gameObjects.get(i).crash()) { // crashed with itself (body)
				cell = gameObjects.get(i).getTile();
			} else { // crashed with other objects?
				for(int j = 0; j < gameObjects.size(); j++)
					if(j != i)
						if(gameObjects.get(i).contains(gameObjects.get(j).getPosition()))
							cell = gameObjects.get(j).getTile(); 
			}
			
			for(int j = 0; j < Tiles.tiles.length; j++) 
				if(Tiles.tiles[j][0] == gameObjects.get(i).getTile() && Tiles.tiles[j][1] == cell)
					manageLogic(i, gameObjects.get(i).updateLogic(Tiles.tiles[j][2]));
					
		} return 0;
	}
	
	public void setOnFinishGameListener(OnFinishGameListener onFinishGameListener) {
		this.onFinishGameListener = onFinishGameListener;
	}
	
	public boolean contains(Point point) {
		return field[point.x][point.y] == Tiles.WALL;
	}
	
	public void onTouch(float x, float y) {
		for(Game game: gameObjects)
			game.onTouch(x*numRows/width, y*numColumns/height);
	}

	public boolean crash() {
		// TODO: NOT USED
		return false;
	}
	
}
