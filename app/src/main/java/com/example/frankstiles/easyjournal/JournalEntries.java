package com.example.frankstiles.easyjournal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.frankstiles.easyjournal.data.JournalContract.JournalEntry;
import com.example.frankstiles.easyjournal.data.JournalDbHelper;

import java.util.ArrayList;

public class JournalEntries extends AppCompatActivity implements  JournalAdapter.ListItemClickListener{

    private JournalAdapter mJournalAdapter;
    private RecyclerView mJournalList;
    private JournalDbHelper mJournalEntries;
    private ImageView mGoogleProfilePic ;
    private TextView mGoogleUsername ;
    private ArrayList<CustomJournalData> mJournalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);



        mGoogleUsername = (TextView)findViewById(R.id.g_username);
        mGoogleUsername.setText(getIntent().getExtras().getString("gUsername"));

        mGoogleProfilePic = (ImageView) findViewById(R.id.g_profile_image);
        Glide.with(this).load(getIntent().getExtras().getString("img_url")).into(mGoogleProfilePic);

        mJournalEntries = new JournalDbHelper(this);
        mJournalList = (RecyclerView) findViewById(R.id.rv_journal_entries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mJournalList.setLayoutManager(layoutManager);
        mJournalList.setHasFixedSize(true);

        setAdapterAndUpdateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalEntries.this, AddJournal.class);
                intent.putExtra("gUsername",getIntent().getExtras().getString("gUsername"));
                intent.putExtra("gId",getIntent().getExtras().getString("gId"));
                intent.putExtra("gEmail",getIntent().getExtras().getString("gEmail"));
                startActivity(intent);
            }
        });
    }

    public void setAdapterAndUpdateUI(){
        ArrayList<CustomJournalData> dataFromDatabase = dataSourceFromDb();
        mJournalAdapter = new JournalAdapter(dataFromDatabase/*dataSource was set from dataFromDbFunction)*/,
                this,JournalEntries.this);
        mJournalList.setAdapter(mJournalAdapter);
    }

    @Override
    protected void onStart() {
        setAdapterAndUpdateUI();
        super.onStart();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        // FOR NOW NOTHING TO DO
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sign_out,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sign_out){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<CustomJournalData> dataSourceFromDb(){

        mJournalDataSource = new ArrayList<CustomJournalData>();
        SQLiteDatabase db = mJournalEntries.getReadableDatabase();

        String[] projection ={
                JournalEntry.COLUMN_TITLE,
                JournalEntry.COLUMN_DESCRIPTION,
                JournalEntry.COLUMN_TIME,
                JournalEntry.COLUMN_DATE
        };

        String selection = JournalEntry.G_ID +" = ? ";
        String[]  selectionArgs =  {getIntent().getExtras().getString("gId")};

       Cursor cursor = db.query(
                 JournalEntry.TABLE_JOURNALS_NAME,
                 projection,
                 selection,
                 selectionArgs,
                null,
                null,
                null
        );

       try{
            //GETS TABLE COLUMN INDEX VIA CURSOR
            int titleIndex =  cursor.getColumnIndex(JournalEntry.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(JournalEntry.COLUMN_DESCRIPTION);
            int timeIndex = cursor.getColumnIndex(JournalEntry.COLUMN_TIME);
            int dateIndex = cursor.getColumnIndex(JournalEntry.COLUMN_DATE);


            while(cursor.moveToNext()){
                String titleValue = cursor.getString(titleIndex);
                String descriptionValue = cursor.getString(descriptionIndex);
                String timeValue = cursor.getString(timeIndex);
                String dateValue = cursor.getString(dateIndex);

               mJournalDataSource.add(new CustomJournalData(titleValue, descriptionValue , timeValue,dateValue));
            }
        }finally{
            cursor.close();
        }

        return mJournalDataSource;

    }
}
