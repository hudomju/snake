package com.moped.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
	}
	
	/** Called from xml */
	public void onPlayClick(View v) {
		startActivity(new Intent(this, SnakeActivity.class));
	}
	
	/** Called from xml */
	public void onCreateLevelClick(View v) {
		Toast.makeText(this, "On create level", Toast.LENGTH_SHORT).show();
	}
	
	/** Called from xml */
	public void onGetScoresClick(View v) {
		Toast.makeText(this, "On get scores", Toast.LENGTH_SHORT).show();
	}
	
	/** Called from xml */
	public void onGetLevelsClick(View v) {
		Toast.makeText(this, "On get levels", Toast.LENGTH_SHORT).show();
	}
	
	

}
