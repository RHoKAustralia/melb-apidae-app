package com.example.apidae.db;

import com.example.apidae.domain.Story;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoryDBHelper extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Adiae";
 
    // Contacts table name
    private static final String TABLE_STORY = "story";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "story_id";
    private static final String KEY_NAME = "name";
    
    
    public StoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
	}


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
 
        // Create tables again
        onCreate(db);
    }

    public void addStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(KEY_ID, story.get_id()); 
        values.put(KEY_NAME, story.get_story_name()); 
     
        // Inserting Row
        db.insert(TABLE_STORY, null, values);
        db.close(); // Closing database connection
    }
}
