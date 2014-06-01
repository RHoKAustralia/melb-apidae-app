package com.example.apidae;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.apidae.db.StoryDBHelper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
		if(null != extras){
			String vName = extras.getString(VILLAGE_NAME_STRING);
			villageName.setText(vName);
			int rank = extras.getInt(VILLAGE_RANKING_INT);
			switch(rank){
				case 1:
					//villageRanking.setImageResource(R.drawable)
					break;
			}
		}
		
		PopulateStoryList();
	}
	
	private void PopulateStoryList(){
		StoryDBHelper db = new StoryDBHelper(this);
		List<String> imagePaths = new ArrayList<String>();
		//if(db.getPicturesCount())
		
		//for(int i=0; i<)
		
		ArrayList<Bitmap> storiesImages = new ArrayList<Bitmap>();
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
	            storiesImages.add(bMap);
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
	}
	
//	public void addStories()
}
