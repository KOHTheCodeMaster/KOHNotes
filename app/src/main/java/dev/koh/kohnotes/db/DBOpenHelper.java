package dev.koh.kohnotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //  Database
    private static final String DB_NAME = "kohnotes.db";
    private static final int DB_VER = 1;

    public static final String NOTE_TEXT = "noteText";
    private static final String NOTE_ID = "_id";
    //  Constants for table
    static final String TABLE_NAME = "notes";
    static final String NOTE_TIME_STAMP = "noteTimeStamp";

    public static final String[] ALL_COLUMNS = {NOTE_ID, NOTE_TEXT, NOTE_TIME_STAMP};

    //  SQL Query for creating notes table
    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOTE_TEXT + " TEXT," +
                    NOTE_TIME_STAMP + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
