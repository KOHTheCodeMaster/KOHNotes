package dev.koh.kohnotes;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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
//        addNewNote(note1);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kebab_menu_hp, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.idCreateSampleNotesKebabMenuHP:
                addNewNote("Sample Note #1");
                addNewNote("Sample Note #2\nAbCdEf...XyZ");
                addNewNote("Sample Note #3\nAbCdEf...XyZ\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

                restartLoader();

                break;

            case R.id.idDeleteAllKebabMenuHP:
                deleteAllNotes();
                break;
            default:
                Log.d(LOG_TAG, "<onOptionsItemSelected> Kebab Options Menu Clicked");
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayToastMsg(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void restartLoader() {
        LoaderManager.getInstance(this).restartLoader(0, null, this);
    }

    private void deleteAllNotes() {

        DialogInterface.OnClickListener onDeleteAllNotesClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {

                    getContentResolver().delete(NotesProvider.CONTENT_URI,
                            null, null);

                    restartLoader();
                    displayToastMsg("Deleted All Notes!");

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_all_prompt));
        builder.setPositiveButton(android.R.string.yes, onDeleteAllNotesClickListener);
        builder.setNegativeButton(android.R.string.no, onDeleteAllNotesClickListener);
        builder.show();

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
 *  Last Updated : 11th July 2K19, 06:28 PM..!!
 *
 *  Change Log:
 *  <| ================================================================ |>
 *
 *  5th Commit - [Kebab Menu Added]
 *  1. Create Sample Notes
 *  2. Delete All Notes
 *
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