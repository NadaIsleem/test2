package com.example.bg_media_test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mixberrymedia.constants.AdLength;
import com.mixberrymedia.constants.AdType;
import com.mixberrymedia.constants.MusicGenre;
import com.mixberrymedia.debug.VSLogger;
import com.mixberrymedia.error.MBAdRequestError;
import com.mixberrymedia.sdk.ConfigurationObject;
import com.mixberrymedia.sdk.IMbSdkHandler;
import com.mixberrymedia.sdk.MbSdkService;

public class MainActivity extends Activity implements IMbSdkHandler {
	Button b1, b2, lp, p, l;
	static MbSdkService service;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.activity_main);
		service = new MbSdkService(this, "u1uc7147jakg9gcf3ou1", this);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		lp = (Button) findViewById(R.id.lp);
		l = (Button) findViewById(R.id.l);
		p = (Button) findViewById(R.id.p);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, Play.class);
				startService(i);
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(MainActivity.this, Play.class);
				stopService(i1);
				ConfigurationObject o = new ConfigurationObject();
				o.setAdLength(AdLength.AD_PERIOD_LONG);
				o.setGenre(MusicGenre.AFRICA);
				o.setType(AdType.AUDIO_WITH_BANNER);
				service.loadAndPlayAd(o);
			}
		});
	
	
	lp.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i1 = new Intent(MainActivity.this, Play.class);
			stopService(i1);
			ConfigurationObject o = new ConfigurationObject();
			o.setAdLength(AdLength.AD_PERIOD_LONG);
			o.setGenre(MusicGenre.AFRICA);
			o.setType(AdType.AUDIO_WITH_BANNER);
			service.loadAndPlayAd(o);
		}
	});


l.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i1 = new Intent(MainActivity.this, Play.class);
		stopService(i1);
		ConfigurationObject o = new ConfigurationObject();
		o.setAdLength(AdLength.AD_PERIOD_LONG);
		o.setGenre(MusicGenre.AFRICA);
		o.setType(AdType.AUDIO_WITH_BANNER);
		service.loadAd(o);
	}
});


p.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i1 = new Intent(MainActivity.this, Play.class);
		stopService(i1);
		ConfigurationObject o = new ConfigurationObject();
		o.setAdLength(AdLength.AD_PERIOD_LONG);
		o.setGenre(MusicGenre.AFRICA);
		o.setType(AdType.AUDIO_WITH_BANNER);
		service.playAd();
	}
});
}

	public static void onSongComplete() {

		ConfigurationObject o = new ConfigurationObject();
		// o.setAdLength(AdLength.AD_PERIOD_LONG);
		o.setGenre(MusicGenre.AFRICA);
		o.setType(AdType.AUDIO_WITH_BANNER);
		service.loadAndPlayAd(o);
	}

	@Override
	public void onPause() {
		VSLogger.writeLog("*******************", "onPause");
		service.pauseSDK();
		super.onPause();
	}

	@Override
	public void onResume() {
		VSLogger.writeLog("*******************", "onResume");
		// service.onSdkResume();
		super.onResume();
	}

	@Override
	public void onAdLoaded() {
		VSLogger.writeLog("*******************", "loaded");

	}

	@Override
	public void onFailedToLoadAd(MBAdRequestError e) {
		VSLogger.writeLog("*******************",
				"Error load " + e.getErrorCode() + " message " + e.getMessage());

	}

	@Override
	public void onAdReceived() {
		VSLogger.writeLog("*******************", "onAdReceived");
	}

	@Override
	public void onFetchingAd() {
		VSLogger.writeLog("*******************", "onFetchingAd");
	}

	@Override
	public void onFailedToReceiveAd(MBAdRequestError e) {
		VSLogger.writeLog("*******************", "Error " + e.getErrorCode()
				+ " message " + e.getMessage());
	}

}
