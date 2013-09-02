package com.example.bg_media_test;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.IBinder;
import android.widget.MediaController;

public class Play extends Service implements OnPreparedListener,
		OnCompletionListener {
	MediaPlayer mediaPlayer;
	AudioManager audio;

	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate() {
		
		super.onCreate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		String url = "http://chinvi.org/Music.mp3"; // your URL here
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare(); // might take long! (for buffering, etc)

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * mp.setLooping(false); mp.setVolume(3.90f,3.90f);
		 */
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int max = audio.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
		mediaPlayer.setVolume(max, max);

		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(null);

		// mc.setMediaPlayer(mp);

		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setOnCompletionListener(this);
		// this.setsetVolumeControlStream(AudioManager.STREAM_MUSIC);

		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	public void onDestroy() {

		super.onDestroy();
		mediaPlayer.stop();

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		MainActivity.onSongComplete();

	}

}
