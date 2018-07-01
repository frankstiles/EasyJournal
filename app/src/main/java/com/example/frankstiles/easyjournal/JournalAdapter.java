package com.example.frankstiles.easyjournal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankStiles on 28-Jun-18.
 */

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder>{


    private ArrayList<CustomJournalData> mCustomJournalData;
    private ListItemClickListener mOnClickListener;
    private Context  mContext;

    //INTERFACE DECLARED TO HANDLE CLICK EVENTS
    public interface ListItemClickListener{
        void  onListItemClick(int clickedItemIndex);
    }


    public JournalAdapter(ArrayList<CustomJournalData> journalData, ListItemClickListener listener,Context context){
        mCustomJournalData = journalData;
        mOnClickListener = listener;
        mContext = context;

    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_journal_item_entry, parent,false);

        //Passes the inflated View to the View Holder Subclass
        JournalViewHolder viewHolder = new JournalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {

        CustomJournalData journaldata = mCustomJournalData.get(position); //gets data

        holder.mEventDescription.setText(journaldata.getmEventDescription());
        holder.mEvent.setText(journaldata.getmEventTitle());
        holder.mEventDate.setText(journaldata.getmEventDate());
        holder.mEventTime.setText(journaldata.getmEventTime());
    }

    @Override
    public int getItemCount() {
        return mCustomJournalData.size();
    }

/*** VIEW HOLDER SUBCLASS FOR CREATING VIEW HOLDERS**/
    class JournalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mEventDescription;
        TextView mEvent;
        TextView mEventDate;
        TextView mEventTime;

        Context ctx;

        public JournalViewHolder(View itemView/*VIEW WOULD BE PASSED ON OnCreateViewHolder*/){
            super(itemView);
            //FIND ALL VIEW BY ID IN JOURNAL ITEM
            mEventDescription = (TextView) itemView.findViewById(R.id.event_description);
            mEvent = (TextView) itemView.findViewById(R.id.event_title);
            mEventDate = (TextView) itemView.findViewById(R.id.event_date);
            mEventTime = (TextView) itemView.findViewById(R.id.event_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            CustomJournalData customJournalData = mCustomJournalData.get(clickedPosition);

            //mOnClickListener.onListItemClick(clickedPosition);


            Intent i = new Intent(mContext, ViewDetails.class);
            i.putExtra("time",customJournalData.mEventTime);
            i.putExtra("title",customJournalData.mEventTitle);
            i.putExtra("description",customJournalData.mEventDescription);
            mContext.startActivity(i);
        }
    }
}
