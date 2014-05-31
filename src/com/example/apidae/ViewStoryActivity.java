package com.example.apidae;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class ViewStoryActivity extends Activity{
	
	public final static String STORY_NAME_STRING = "StoryName";
	public final static String IMAGES_STRING_LIST = "Images";
	public final static String AUDIO_URI_STRING = "AudioUri";
	public final static String TAG_RID_LIST = "TagsList";
	
	LinearLayout imageScroll;
	LinearLayout audioScroll;
	LinearLayout tagScroll;
	
	TextView storyName;
	
	Uri audioUri;
	MediaPlayer audioPlayer;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_story);
		
		imageScroll = (LinearLayout)findViewById(R.id.photoScrollView);
		audioScroll = (LinearLayout)findViewById(R.id.audioScrollView);
		tagScroll = (LinearLayout)findViewById(R.id.tagScrollView);
		storyName = (TextView)findViewById(R.id.storyNameFieldView);
		
		Bundle extras = getIntent().getExtras();
		if(null != extras){
			storyName.setText(extras.getString(STORY_NAME_STRING));
			
			// Photo parsing
			ArrayList<String> imagePaths = extras.getStringArrayList(IMAGES_STRING_LIST);
			ArrayList<Bitmap> images = new ArrayList<Bitmap>();
			for(String path : imagePaths){
				try{
					FileInputStream in;
			        BufferedInputStream buf;
			        in = new FileInputStream(path);
		            buf = new BufferedInputStream(in);
		            byte[] bMapArray;
					bMapArray = new byte[buf.available()];
		            buf.read(bMapArray);
		            Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
		            images.add(bMap);
		            if (in != null) {
		             	in.close();
	                }
	                if (buf != null) {
		             	buf.close();
	                }
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "Error Reading Images", Toast.LENGTH_LONG).show();
				}
			}
			updatePhotoScroll(images);
			
			// audio parsing
			String audioPath = extras.getString(AUDIO_URI_STRING);
			audioUri = Uri.parse(audioPath);
			updateAudioScroll();
			
			// tag parsing
			
		}
	}
	
	private void updateAudioScroll(){
		audioScroll.removeAllViews();
		ImageButton iB = new ImageButton(this);
		iB.setImageResource(R.drawable.audio_button_unpressed);
		iB.setScaleType(ScaleType.FIT_CENTER);
		iB.setPadding(10, 10, 10, 10);
		iB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null == audioPlayer){
					audioPlayer = new MediaPlayer();
					audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
					
					try {
						audioPlayer.setDataSource(getApplicationContext(), audioUri);
					} catch (IllegalArgumentException e) {
						Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
					} catch (SecurityException e) {
						Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
					} catch (IllegalStateException e) {
						Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						audioPlayer.prepare();
					} catch (IllegalStateException e) {
						Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
					}
					audioPlayer.start();
				}else{
					if(audioPlayer.isPlaying()){
						audioPlayer.stop();
					}else{
						try {
							audioPlayer.setDataSource(getApplicationContext(), audioUri);
						} catch (IllegalArgumentException e) {
							Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
						} catch (SecurityException e) {
							Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
						} catch (IllegalStateException e) {
							Toast.makeText(getApplicationContext(), "Could not play Audio", Toast.LENGTH_LONG).show();
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							audioPlayer.prepare();
						} catch (IllegalStateException e) {
							Toast.makeText(getApplicationContext(), "Illegal State", Toast.LENGTH_LONG).show();
						} catch (IOException e) {
							Toast.makeText(getApplicationContext(), "IO Exception", Toast.LENGTH_LONG).show();
						}
						audioPlayer.start();
					}
				}
			}
		});
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		audioScroll.addView(iB, lp);
	}
	
	private void updatePhotoScroll(List<Bitmap> photos){
		imageScroll.removeAllViews();
		for( Bitmap b : photos){
			ImageView picB = new ImageView(this);
			picB.setImageBitmap(b);
			picB.setScaleType(ScaleType.FIT_CENTER);
			LayoutParams lp = new LayoutParams((int)(b.getWidth()*2), LayoutParams.MATCH_PARENT, 1);
			imageScroll.addView(picB, lp);
		}
	}
}
