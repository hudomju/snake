package com.moped.snake.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.moped.snake.R;

import android.content.Context;

class LocalFieldLoader implements FieldLoader {
	
	private final Context context;
	
	public LocalFieldLoader(final Context context) {
		this.context = context;
	}

	public String load() {
		InputStream inputStream = context.getResources().openRawResource(R.raw.level);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		int i;
		try {
			i = inputStream.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		return byteArrayOutputStream.toString();
	}

}
