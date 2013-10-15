package com.moped.snake.communication;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class HttpPoster extends AsyncTask<Void, Void, Boolean> {
	
	private final String name;
	private final String score;
	private final Context context;
	private static final String URL = "http://s296899600.mialojamiento.es/onlineScoreBoard/sendScore.php?";
	
	public HttpPoster(Context context, String name, String score) {
		this.name = name;
		this.score = score;
		this.context = context;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
    	
        try{
        	HttpClient httpclient = new DefaultHttpClient();
        	HttpPost httppost = new HttpPost(URL + "name=" + name + "&score=" + score);
        	httpclient.execute(httppost);

    	}catch (Exception e) {
			Log.e("log_tag","Error en la conexi—n"+e.toString());
			return false;
		}  
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result)
			Toast.makeText(context, "Result posted", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(context, "The result was not posted. Check your network connection", Toast.LENGTH_SHORT).show();
	}
	

}
