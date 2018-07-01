package com.example.frankstiles.easyjournal;

/**
 * Created by FrankStiles on 28-Jun-18.
 */

public class CustomJournalData {
    public String mEventTitle;
    public String mEventDescription;
    public String mEventTime;
    public String mEventDate;

    public CustomJournalData(String eTitle,String eDescription,String eTime,String eDate){
        mEventTitle = eTitle;
        mEventDescription = eDescription;
        mEventTime = eTime;
        mEventDate = eDate;
    }

    public String getmEventTitle(){return mEventTitle;}
    public String getmEventDescription(){return mEventDescription;}
    public String getmEventTime(){return mEventTime;}
    public String getmEventDate(){return mEventDate;}
}
