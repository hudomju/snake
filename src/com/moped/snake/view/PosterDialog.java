package com.moped.snake.view;

import com.moped.snake.R;
import com.moped.snake.communication.HttpPoster;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PosterDialog {

	private final Context context;
	private final Dialog dialog;
	private final int score;
	
	public PosterDialog(Context context, int score) {
		this.context = context;
		this.score = score;
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.poster_dialog);
	}
	
	public void show() {
		dialog.setTitle("Online scoreboard");
		final Button btSave = (Button) dialog.findViewById(R.id.bt_save); 
		btSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String name = ((EditText) dialog.findViewById(R.id.edit_username)).getText().toString();
				new HttpPoster(context, name, "" + score).execute();
				dialog.dismiss();
			}
		});
		Button btCancel = (Button) dialog.findViewById(R.id.bt_cancel);
		btCancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(context, "Nothing was posted", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		dialog.show();
	}

}
