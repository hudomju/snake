package com.moped.snake.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.moped.snake.model.Food;
import com.moped.snake.model.OnFinishGameListener;
import com.moped.snake.model.Snake;
import com.moped.snake.model.World;
import com.moped.snake.resources.FieldLoader;
import com.moped.snake.resources.FieldLoaderFactory;

public class SnakeView extends LinearLayout implements OnFinishGameListener {
	
	private static final int GAME_SPEED = 300;
	private Update updateRunnable;
	private final Paint paint;
	private final Paint resultPaint;
	private int score = 0;
	private World world;
	private boolean endGame = false;

	
	public SnakeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		resultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		resultPaint.setColor(Color.RED);
		resultPaint.setTextSize(80);
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if(endGame) {
			dispatchWinningDraw(canvas);
		} else {
			world.setDimensions(getWidth(), getHeight());
			world.draw(canvas);
		}
		
	}
	
	private int incRows = 15;
	private int incColumns = 30;
	private void dispatchWinningDraw(Canvas canvas) {

		float width = getWidth();
		float height = getHeight();
		
		int rows = 15;
		int columns = 30;
		
		float cWidht = width/rows;
		float cHeight = height/columns;
		
		paint.setColor(Color.BLACK);
		
		int x = 0;
		int y = 0;
		for(int i = 0; i < columns-incColumns; i++) {
			for(int j = 0; j < rows-incRows; j++) {
				if(i%2 == 0) {
					if(j%2 == 0)
						canvas.drawRect(x, y, x+cWidht, y+cHeight, paint);
				} else
					if(j%2 != 0)
						canvas.drawRect(x, y, x+cWidht, y+cHeight, paint);
				x += cWidht;
			}
			x = 0;
			y += cHeight;
		}
		if(incColumns != 0) {
			incColumns-=2;
			incRows--;
			postInvalidateDelayed(30);
		} else {
			canvas.drawText("Game Over", width/10, height/2, resultPaint);
			new PosterDialog(getContext(), score).show();
		}
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initializeWorld();
	}

	private void initializeWorld() {
		
		FieldLoader fieldLoader = FieldLoaderFactory.newFieldLoader(getContext(), FieldLoaderFactory.LOCAL_FIELD_LOADER);
		world = new World(fieldLoader.load());
		
		world.addGameObject(new Food());
		world.addGameObject(new Food());
		
		world.addGameObject(new Snake());
		
		world.setPosition(null);
		world.setOnFinishGameListener(this);
		
		updateRunnable = new Update();
		postDelayed(updateRunnable, GAME_SPEED);
	}
	
	private class Update implements Runnable {
		
		public void run() {
			if(!endGame) {
				world.updatePositions();
				invalidate();
				updateRunnable = new Update();
				postDelayed(updateRunnable, GAME_SPEED);
			}
		}
		
	}
	
	public void onFinishGame(int score) {
		endGame = true;
		this.score = score;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(endGame)
			((Activity)getContext()).finish();
		else
			if(event.getAction() == MotionEvent.ACTION_DOWN)
				world.onTouch(event.getX(), event.getY());
		return super.onTouchEvent(event);
	}

}
