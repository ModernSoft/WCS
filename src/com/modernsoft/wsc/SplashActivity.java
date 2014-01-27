package com.modernsoft.wsc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends Activity implements Runnable
{
	public final int DELAY_SPLASH = 3500;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_splash);
        
        TextView wcs_title = (TextView) findViewById (R.id.wcs_title);
        
        TextView wcs_subtitle = (TextView) findViewById (R.id.wcs_subtitle);
        
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/CoolJazz_Fix.ttf");
        
        wcs_title.setTypeface(face);
        wcs_subtitle.setTypeface(face);
        
        Handler handler = new Handler();
        
        handler.postDelayed(this, DELAY_SPLASH);
    }
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}
	
	@Override
	public void run() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
}
