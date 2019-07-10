package dev.koh.kohnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import dev.koh.kohnotes.db.DBOpenHelper;
import dev.koh.kohnotes.db.NotesProvider;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String note1 = "Hello World..!! ^__^";
        addNewNote(note1);

        init();


    }

    private void init() {

        Cursor cursor = getContentResolver().query(NotesProvider.CONTENT_URI, DBOpenHelper.ALL_COLUMNS,
                null, null, null, null);

        String[] from = {DBOpenHelper.NOTE_TEXT};
//        String [] from = DBOpenHelper.ALL_COLUMNS;
        int[] to = {android.R.id.text1};

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_1, cursor, from, to, 0);

        ListView listView = findViewById(R.id.idNotesListView);
        listView.setAdapter(cursorAdapter);

    }

    private void addNewNote(String noteText) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.NOTE_TEXT, noteText);
        Uri currentNoteUri = getContentResolver().insert(NotesProvider.CONTENT_URI, contentValues);
        Log.d(LOG_TAG, "addNewNote: currentNodeUri id : " + currentNoteUri.getLastPathSegment());

    }

}


/*
 *  Date Created : 9th July 2K19, 09:30 PM..!!
 *  Last Updated : 10th July 2K19, 08:09 PM..!!
 *
 *  Change Log:
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