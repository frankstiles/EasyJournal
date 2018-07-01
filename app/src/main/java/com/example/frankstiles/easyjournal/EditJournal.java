package com.example.frankstiles.easyjournal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frankstiles.easyjournal.data.JournalContract.JournalEntry;
import com.example.frankstiles.easyjournal.data.JournalDbHelper;

import java.util.ArrayList;

public class EditJournal extends AppCompatActivity {


    private String mEventTitle;
    private EditText mEventDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        //mEventTitle = getIntent().getExtras().getString("title").trim();
        String description = getIntent().getExtras().getString("description");


        mEventDescription = (EditText) findViewById(R.id.edit_description);
        mEventDescription.setText(description);

    }


    public boolean updateJournal(){

        JournalDbHelper journalDbHelper = new JournalDbHelper(this);
        SQLiteDatabase db = journalDbHelper.getReadableDatabase();
        String eventString = mEventDescription.getText().toString().trim();

        ContentValues value = new ContentValues();
        value.put(JournalEntry.COLUMN_DESCRIPTION, eventString);


        String selection = JournalEntry.COLUMN_TITLE +" = ? ";
        String[]  selectionArgs =  {getIntent().getExtras().getString("title").trim()};

        long rowId = db.update(JournalEntry.TABLE_JOURNALS_NAME,
                value,selection,selectionArgs);

        if(rowId == -1){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_btn,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save_btn){
            if(updateJournal()){
                Toast.makeText(EditJournal.this,"Successfully Updated to Database ",Toast.LENGTH_SHORT).show();
               /* Intent I = new Intent(EditJournal.this,JournalEntries.class);
                startActivity(I);*/
            }
        }
        return true;
    }



}
