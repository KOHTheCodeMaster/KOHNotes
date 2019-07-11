package dev.koh.kohnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import dev.koh.kohnotes.db.DBOpenHelper;
import dev.koh.kohnotes.db.NotesProvider;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = "MainActivity_1";
    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String note1 = "Hello World..!! ^__^";
        addNewNote(note1);

        init();


    }

    private void init() {

        String[] from = {DBOpenHelper.NOTE_TEXT};
//        String [] from = DBOpenHelper.ALL_COLUMNS;
        int[] to = {android.R.id.text1};

        cursorAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_1, null, from, to, 0);

        ListView listView = findViewById(R.id.idNotesListView);
        listView.setAdapter(cursorAdapter);

        LoaderManager.getInstance(this).initLoader(0, null, this);

    }

    private void addNewNote(String noteText) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.NOTE_TEXT, noteText);
        Uri currentNoteUri = getContentResolver().insert(NotesProvider.CONTENT_URI, contentValues);

        if (currentNoteUri != null)
            Log.d(LOG_TAG, "addNewNote: currentNodeUri id : " + currentNoteUri.getLastPathSegment());

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(this, NotesProvider.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}


/*
 *  Date Created : 9th July 2K19, 09:30 PM..!!
 *  Last Updated : 11th July 2K19, 04:04 PM..!!
 *
 *  Change Log:
 *  <| ================================================================ |>
 *
 *  4th Commit - [LoaderManager - Async DB Requests]
 *  1. Implemented LoaderManager for asynchronous callbacks to DB & performing queries
 *     on separate thread rather than the Main UI Thread.
 *
 *  <| ================================================================ |>
 *
 *  3rd Commit - [CRUD - Content Provider]
 *  1. CRUD Operations handled using Content Provider aka NotesProvider.
 *  2. Displaying All the rows inserted in the DB at startup.
 *
 *  <| ================================================================ |>
 *
 *  2nd Commit - [SQL Database Added]
 *  1. SQLOpenHelper class added to create notes table
 *  2. NotesProvider class added to handle the CRUD Operations
 *
 *  <| ================================================================ |>
 *
 *  Init Commit [Updated Launcher Icon]
 *
 *  <| ================================================================ |>
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */