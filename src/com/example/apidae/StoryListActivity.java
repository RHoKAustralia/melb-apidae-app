package com.example.apidae;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.apidae.db.StoryDBHelper;
import com.example.apidae.domain.Picture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class StoryListActivity extends Activity{
	
	public final static String VILLAGE_NAME_STRING = "VillageName";
	public final static String VILLAGE_RANKING_INT = "VillageRanking";

	LinearLayout storyListLayout;
	TextView villageName;
	ImageView villageRanking;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_list);
		
		storyListLayout = (LinearLayout)findViewById(R.id.storyScrollLayout);
		villageName = (TextView)findViewById(R.id.villageNameText);
		villageRanking = (ImageView)findViewById(R.id.villageRankingImage);
		Bundle extras = getIntent().getExtras();
		Log.d("",extras.toString());
		if(null != extras){
			String vName = extras.getString(VILLAGE_NAME_STRING);
			villageName.setText(vName);
			int rank = extras.getInt(VILLAGE_RANKING_INT);
			switch(rank){
				case 1:
					villageRanking.setImageResource(R.drawable.badge1);
					break;
				case 2:
					villageRanking.setImageResource(R.drawable.badge2);
					break;
				case 3:
					villageRanking.setImageResource(R.drawable.badge3);
					break;
				case 4:
					villageRanking.setImageResource(R.drawable.badge4);
					break;
					
			}
		}
		
		PopulateStoryList();
	}
	
	public void newStory(View v){
		Intent i = new Intent(v.getContext(), CreateStoryActivity.class);
		startActivity(i);
	}
	
	private void PopulateStoryList(){
		StoryDBHelper db = new StoryDBHelper(this);
		List<String> imagePaths = new ArrayList<String>();
		List<Integer> story_ids = db.getAllStoryIds();
		for(int i : story_ids){
			List<Bitmap> bs = new ArrayList<Bitmap>();
			for(Picture p : db.getAllPictures(i)){
				try{
					FileInputStream in;
			        BufferedInputStream buf;
			        in = new FileInputStream(p.getPicture_path());
		            buf = new BufferedInputStream(in);
		            byte[] bMapArray;
					bMapArray = new byte[buf.available()];
		            buf.read(bMapArray);
		            Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
		            bs.add(bMap);
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
			
			LinearLayout tll = new LinearLayout(this);
			tll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			tll.setOrientation(LinearLayout.HORIZONTAL);
			
			ImageView picB = new ImageView(this);
			picB.setImageBitmap(bs.get(0));
			picB.setScaleType(ScaleType.FIT_CENTER);
			LayoutParams lp = new LayoutParams((int)(bs.get(0).getWidth()*2), LayoutParams.MATCH_PARENT, 1);
			tll.addView(picB, lp);
			
			TextView tV = new TextView(this);
			tV.setText("Story N");
			tV.setGravity(Gravity.CENTER);
			LayoutParams ltV = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			tll.addView(tV, ltV);
			
			storyListLayout.addView(tll);
		}
	}
	
//	public void addStories()
}
