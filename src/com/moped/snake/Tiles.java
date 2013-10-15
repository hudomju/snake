package com.moped.snake;

public class Tiles {
	
	/* Constants for the diferent file types. */
	public static final char EMPTY = ' ';
	public static final char WALL  = '#';
	public static final char FOOD  = '$';
	public static final char SNAKE = '*';

	
	public static final char[][] tiles = {
			{FOOD, FOOD, 	FOOD},
			{FOOD, SNAKE, 	SNAKE},
			{FOOD, WALL, 	FOOD},
			{FOOD, EMPTY,	FOOD},
			
			{SNAKE, SNAKE, 	SNAKE},
			{SNAKE, FOOD, 	FOOD},
			{SNAKE, WALL, 	WALL},
			{SNAKE, EMPTY,	EMPTY},
			
	};
	
	
}
