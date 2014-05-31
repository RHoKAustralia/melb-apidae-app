package com.example.apidae;

import android.app.Activity;
import android.os.Bundle;

public class LocationSelectActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
	
	// Konrad made this comment to check that GIT works.
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void viewVillage(View view) {
        Intent intent = new Intent(this, ViewVillageActivity.class);
        startActivity(intent);
    }
}
