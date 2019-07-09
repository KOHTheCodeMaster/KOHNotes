package dev.koh.kohnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


/*
 *  Date Created : 9th July 2K19, 09:30 PM..!!
 *  Last Updated : 9th July 2K19, 10:41 PM..!!
 *
 *  Change Log:
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