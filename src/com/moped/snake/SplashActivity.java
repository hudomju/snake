package com.moped.snake;

import com.moped.snake.animation.Rotate3dAnimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class SplashActivity extends Activity {
	
	private View view;
	private ImageView logoImage;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    
    private void init() {
    	view = View.inflate(this, R.layout.splash_activity, null);
        setContentView(view);
        
        logoImage = (ImageView) view.findViewById(R.id.img_logo);

        applyAnimation();
        view.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startHomeActivity();				
			}
		});
    }
    
    /**
     * Setup a new 3D rotation on the container view.
     *
     * @param position the item that was clicked to show a picture, or -1 to show the list
     * @param start the start angle at which the rotation must begin
     * @param end the end angle of the rotation
     */
    private void applyAnimation() {
    	
        // Find the center of the container
        final float centerX = 100;
        final float centerY = 0;
        final float start = 0;
        final float end   = 360;
        
        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotate =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotate.setInterpolator(new AccelerateInterpolator());
        
        final ScaleAnimation scale = new ScaleAnimation(10.0f, 2.0f, 10.0f, 2.0f);
        
        final TranslateAnimation translate = new TranslateAnimation(-1000.0f, -100.0f, 0.0f, 0.0f);
        
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(scale);
        animationSet.addAnimation(translate);
        animationSet.setFillAfter(true);
        animationSet.setDuration(2000);
        animationSet.setAnimationListener(firstAnimationSetListener);
        
        logoImage.startAnimation(animationSet);
    }
    
    private final AnimationListener firstAnimationSetListener = new AnimationListener() {
		
		public void onAnimationStart(Animation animation) {
			
		}
		
		public void onAnimationRepeat(Animation animation) {
			
		}
		
		public void onAnimationEnd(Animation animation) {
	        final TranslateAnimation translate = new TranslateAnimation(0.0f, -100.0f, 0.0f, 0.0f);
	        final ScaleAnimation scale = new ScaleAnimation(1.0f, 9.0f, 1.0f, 8.0f);
	        final AnimationSet animationSet = new AnimationSet(true);
	        animationSet.setFillAfter(true);
	        animationSet.addAnimation(translate);
	        animationSet.addAnimation(scale);
	        animationSet.setInterpolator(new AccelerateInterpolator());
	        animationSet.setDuration(2000);
	        animationSet.setAnimationListener(lastAnimationSetListener);
	        logoImage.startAnimation(animationSet);
		}
	};
	
	private final AnimationListener lastAnimationSetListener = new AnimationListener() {
		
		public void onAnimationStart(Animation animation) {
			
		}
		
		public void onAnimationRepeat(Animation animation) {
			
		}
		
		public void onAnimationEnd(Animation animation) {
	        startHomeActivity();
		}
	};
	
	public void startHomeActivity() {
		if(hasWindowFocus())
			startActivity(new Intent(SplashActivity.this, HomeActivity.class));
	}
 
}