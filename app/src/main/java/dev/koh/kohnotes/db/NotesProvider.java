package dev.koh.kohnotes.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NotesProvider extends ContentProvider {

    private static final String AUTHORITY = "dev.koh.notes.notesprovider";
    private static final String BASE_PATH = "notes";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    //  Constants to identify the operations to be performed
    private static final int ADD = 1;
    private static final int SEARCH = 2;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, ADD);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", SEARCH);
    }

    SQLiteDatabase database;

    @Override
    public boolean onCreate() {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
        database = dbOpenHelper.getWritableDatabase();


        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
