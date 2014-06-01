package com.example.apidae.db;

import java.util.ArrayList;
import java.util.List;

import com.example.apidae.domain.Picture;
import com.example.apidae.domain.Story;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoryDBHelper extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "Adiae";
 
    // Contacts table name
    private static final String TABLE_STORY = "story";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "story_id";
    private static final String KEY_NAME = "name";

    String CREATE_STORY_TABLE = "CREATE TABLE " + TABLE_STORY + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
    
    //Picture table
    
    // Contacts table name
    private static final String TABLE_PICTURE = "picture";
 
    // Contacts Table Columns names
    private static final String KEY_PIC_PATH = "picture_path";
    
    String CREATE_PICTURE_TABLE = "CREATE TABLE " + TABLE_PICTURE + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PIC_PATH + " TEXT" + ")"; 

   
    public StoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORY_TABLE);
        db.execSQL(CREATE_PICTURE_TABLE);
	}


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE);
        
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
    
    public void addPictureinDB(Picture picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(KEY_ID, picture.getStory_id()); 
        values.put(KEY_PIC_PATH, picture.getPicture_path()); 
     
        // Inserting Row
        db.insert(TABLE_PICTURE, null, values);
        db.close(); // Closing database connection
    }
    
    public List<Picture> getAllPictures(int story_id) {
        List<Picture> pictureList = new ArrayList<Picture>();

        String selectQuery = "SELECT  * FROM " + TABLE_PICTURE+" where "+KEY_ID+"="+story_id;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        if (cursor.moveToFirst()) {
            do {
                Picture picture = new Picture();
                
                picture.setStory_id(Integer.parseInt(cursor.getString(0)));
                picture.setPicture_path(cursor.getString(1));
                pictureList.add(picture);
            } while (cursor.moveToNext());
        }
     
        // return list
        return pictureList;
    }

    public int getPicturesCount(int story_id) {
    	
        String countQuery = "SELECT  * FROM " +TABLE_PICTURE +" where "+KEY_ID+"="+story_id;
        
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.rawQuery(countQuery, null);
        
        //cursor.close();
 
        // return count
        return cursor.getCount();
    }
}
