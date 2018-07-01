package com.example.frankstiles.easyjournal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.frankstiles.easyjournal.data.JournalContract.JournalEntry;

/**
 * Created by FrankStiles on 29-Jun-18.
 */

public class JournalDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "journalDb";
    private static final int DATABASE_VERSION = 1;

    public JournalDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //would still adjust here when am able to get users contact details and name from Google Authentication
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE "+ JournalEntry.TABLE_JOURNALS_NAME + "("
        + JournalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + JournalEntry.G_ID + " TEXT,"
        + JournalEntry.COLUMN_NAME + " TEXT,"
        + JournalEntry.COLUMN_EMAIL + " TEXT,"
        + JournalEntry.COLUMN_TITLE + " TEXT NOT NULL,"
        + JournalEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
        + JournalEntry.COLUMN_TIME + " TEXT,"
        + JournalEntry.COLUMN_DATE + " TEXT );";

        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
