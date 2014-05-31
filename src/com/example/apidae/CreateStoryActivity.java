package com.example.apidae;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class CreateStoryActivity extends Activity{
	
	EditText storyNameField;
	HorizontalScrollView imageScroll;
	HorizontalScrollView audioScroll;
	HorizontalScrollView tagScroll;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_story);
		
		
	}
}
