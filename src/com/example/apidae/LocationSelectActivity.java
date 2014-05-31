package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LocationSelectActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
	
	// Konrad made this comment to check that GIT works.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.village_list);
    }

    public void viewVillage(View view) {
        Intent intent = new Intent(this, ViewVillageActivity.class);
        startActivity(intent);
    }
}
