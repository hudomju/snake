package com.moped.snake.resources;

import android.content.Context;

public class FieldLoaderFactory {
	
	public static final int MOCK_FIELD_LOADER 	 = 0;
	public static final int LOCAL_FIELD_LOADER	 = 1;
	public static final int USER_FIELD_LOADER 	 = 2;
	public static final int SERVER_FIELD_LOADER = 3;
	
	public static FieldLoader newFieldLoader(Context context, int type) {
		if(type == MOCK_FIELD_LOADER)
			return new MockFieldLoader();
		else if(type == LOCAL_FIELD_LOADER)
			return new LocalFieldLoader(context);
		else if(type == USER_FIELD_LOADER)
			return new UserFieldLoader();
		else if(type == SERVER_FIELD_LOADER)
			return new ServerFieldLoader();
		else return null;
	}
	
}
