package com.example.apidae;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.apidae.db.VillageDBAdapter;

public class LocationSelectActivity extends Activity {

    private VillageDBAdapter villageDbHelper;
    private SimpleCursorAdapter dataAdapter;

    /**
     * Called when the activity is first created.
     */
	
	// Konrad made this comment to check that GIT works.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_village_list);

        villageDbHelper = new VillageDBAdapter(this);
        villageDbHelper.open();

        villageDbHelper.deleteAllVillages();
        villageDbHelper.insertSomeVillages();

        displayListView();
    }

    public void viewVillage(View view) {
        Intent intent = new Intent(this, ViewVillageActivity.class);
        startActivity(intent);
    }

    private void displayListView() {


        Cursor cursor = villageDbHelper.fetchAllVillages();

        // The desired columns to be bound
        String[] columns = new String[] {
                VillageDBAdapter.KEY_VILLAGE_NAME,
                VillageDBAdapter.KEY_RANK
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.name,
                R.id.rank
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.village_list,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow("code"));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

            }
        });


        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return villageDbHelper.fetchVillagesByName(constraint.toString());
            }
        });

    }
}

