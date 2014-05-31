package com.example.apidae.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VillageDBAdapter {

    public static final String KEY_ROWID = "village_id";
    public static final String KEY_VILLAGE_NAME = "village_name";
    public static final String KEY_RANK = "continent";

    private static final String TAG = "VillageDBAdapter";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    private static final String DATABASE_NAME = "Apidae";
    private static final String SQLITE_TABLE = "Village";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_VILLAGE_NAME + "," +
                    KEY_RANK + "));";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public VillageDBAdapter(Context ctx) {
        this.context = ctx;
    }

    public VillageDBAdapter open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    public long createVillage(String name, int rank) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_VILLAGE_NAME, name);
        initialValues.put(KEY_RANK, rank);

        return sqLiteDatabase.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllVillages() {

        int doneDelete = 0;
        doneDelete = sqLiteDatabase.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchVillagesByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = sqLiteDatabase.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_VILLAGE_NAME, KEY_RANK},
                    null, null, null, KEY_RANK, null);

        }
        else {
            mCursor = sqLiteDatabase.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_VILLAGE_NAME, KEY_RANK},
                    KEY_VILLAGE_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllVillages() {

        Cursor mCursor = sqLiteDatabase.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                KEY_VILLAGE_NAME, KEY_RANK},
                null, null, null, KEY_RANK, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeVillages() {

        createVillage("Lautoka",1);
        createVillage("Nadi",2);
        createVillage("Momi",3);
        createVillage("Sigatoka",4);

    }

}