package com.example.apidae;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class StoryListActivity extends Activity{
	
	public final static String VILLAGE_NAME_STRING = "VillageName";
	public final static String VILLAGE_RANKING_INT = "VillageRanking";

	LinearLayout storyListLayout;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_list);
		
		storyListLayout = (LinearLayout)findViewById(R.id.storyScrollLayout);
	}
	
//	public void addStories()
}
