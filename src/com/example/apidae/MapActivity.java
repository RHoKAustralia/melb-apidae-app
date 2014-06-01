package com.example.apidae;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MapActivity extends Activity {
	
	private GoogleMap map;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		try {
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	private void initializeMap() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			
			if (map == null) {
				Toast.makeText(getApplicationContext(), "Unable to create map", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
