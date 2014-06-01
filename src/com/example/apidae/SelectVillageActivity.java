package com.example.apidae;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.apidae.domain.Village;

public class SelectVillageActivity extends ListActivity {

    private final String[] VILLAGE_NAMES = new String[]{"Lautoka", "Nadi", "Momi", "Sigatoka"};

    private final Village[] VILLAGES = new Village[]{
            new Village("Lautoka", 1),
            new Village("Nadi", 2),
            new Village("Momi", 3),
            new Village("Sigatoka", 4)};

    /**
     * Called when the activity is first created.
     */
	
	// Konrad made this comment to check that GIT works.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new VillageArrayAdapter(this, VILLAGE_NAMES, VILLAGES));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int rank = position+1;
                viewVillageStoryList(rankToName(rank),rank);
            }
        });
    }

    private String rankToName(int position) {
        Village village = VILLAGES[1];
        for (Village v : VILLAGES) {
            if (v.getRank() == position) village = v;
        }
        return village.getName();
    }

    public void viewVillageStoryList(String name, int rank) {
        Intent intent = new Intent(this, StoryListActivity.class);
        Bundle villageInfo = new Bundle();
        villageInfo.putString(StoryListActivity.VILLAGE_NAME_STRING, name);
        villageInfo.putInt(StoryListActivity.VILLAGE_RANKING_INT, rank);
        intent.putExtras(villageInfo);
        startActivity(intent);
    }

}

