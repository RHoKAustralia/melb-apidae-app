package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewVillageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewVillage);
    }

    public void createStory(View view) {
        Intent intent = new Intent(this, CreateStoryActivity.class);
        startActivity(intent);
    }

}