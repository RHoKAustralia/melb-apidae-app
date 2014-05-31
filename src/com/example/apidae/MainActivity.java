package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void viewVillages(View view) {
        Intent intent = new Intent(this, LocationSelectActivity.class);
        startActivity(intent);
    }
}