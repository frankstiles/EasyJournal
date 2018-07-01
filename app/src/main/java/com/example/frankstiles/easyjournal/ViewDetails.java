package com.example.frankstiles.easyjournal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frankstiles.easyjournal.data.JournalContract;
import com.example.frankstiles.easyjournal.data.JournalDbHelper;

public class ViewDetails extends AppCompatActivity {
    private TextView mTitle;
    private TextView mTime;
    private TextView mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        mTime = (TextView) findViewById(R.id.vd_time);
        mTitle = (TextView) findViewById(R.id.vd_title);
        mDescription = (TextView) findViewById(R.id.vd_description);

        updateUI();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu,menu);
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = mTitle.getText().toString();
        String description  = mDescription.getText().toString();
        int id = item.getItemId();
        if(id == R.id.delete_menu){

            if(deleteJournal()){
                Toast.makeText(ViewDetails.this,"Successfully Deleted from Database ",Toast.LENGTH_SHORT).show();
                finish();
            }
        }else if(id == R.id.edit_menu){
            Intent i = new Intent(ViewDetails.this,EditJournal.class );
            //get title text
            i.putExtra("title",title);
            i.putExtra("description",description);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean deleteJournal(){

        JournalDbHelper journalDbHelper = new JournalDbHelper(this);

        SQLiteDatabase db = journalDbHelper.getReadableDatabase();

        String selection = JournalContract.JournalEntry.COLUMN_TITLE +" = ? ";

        String titleText = mTitle.getText().toString().trim();
        String[]  selectionArgs =  {titleText};

        long rowId = db.delete(JournalContract.JournalEntry.TABLE_JOURNALS_NAME,selection,selectionArgs);

        if(rowId == -1){
            return false;
        }else{
            return true;
        }
    }

    // sets value to display for activity view details.......
    public void updateUI(){
        String time = getIntent().getExtras().getString("time");
        String title = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");

        mTime.setText(time);
        mTitle.setText(title);
        mDescription.setText(description);
    }
}
