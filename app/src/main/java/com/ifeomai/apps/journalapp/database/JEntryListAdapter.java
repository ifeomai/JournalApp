package com.ifeomai.apps.journalapp.database;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.JEntryDetailActivity;
import com.ifeomai.apps.journalapp.R;

import java.util.ArrayList;

public class JEntryListAdapter extends RecyclerView.Adapter<JEntryListAdapter.JEntryViewHolder> {

    private final JEntryListAdapter.ListItemClickListener mOnClickListener;
    private static final String TAG = JEntryListAdapter.class.getSimpleName();


    private ArrayList<JEntry> mJEntries = new ArrayList<>();
    //I don't think I need this context. But just in case
    //private Context mContext;
    //public JEntryListAdapter(Context context, ArrayList<JEntry> jEntries,JEntryListAdapter.ListItemClickListener listener){
    public JEntryListAdapter(Context context, ArrayList<JEntry> jEntries,JEntryListAdapter.ListItemClickListener listener){
        mOnClickListener= listener;
        mJEntries = jEntries;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    //Here it creates the viewholder using a layout(static/dynamic) and inflates them
    //This is called several times to fill & scroll the screen or display all items which ever is smaller
    @Override
    public JEntryListAdapter.JEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_jentry;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        JEntryListAdapter.JEntryViewHolder viewHolder = new JEntryListAdapter.JEntryViewHolder(view);

        return viewHolder;
    }

    /*   * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * This is where the adapter populates the View with data from our model*/
    @Override
    public void onBindViewHolder(@NonNull JEntryListAdapter.JEntryViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(mJEntries.get(position));
    }

    //return the number of items available for display
    @Override
    public int getItemCount(){
        return mJEntries.size();
    }






    //*******************************************
    //******* VIEW HOLDER DEFINITION ************
    //*******************************************
    //Cache of the children Views for a list item
    // This looks like a duplication of my viewHolder.JEntryViewHolder
    //*******************************************
    class JEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleView;
        private TextView updatedAtView;
        private TextView descriptionView;

        private Context mContext;
        public JEntryViewHolder(View itemView){
            super(itemView);
            mContext = itemView.getContext();

            //TODO 1. Find the Views in the item_jentry layout
            titleView = itemView.findViewById(R.id.post_title);
            itemView.setOnClickListener(this);
        }
        void bind(JEntry jEntry)
        {
            //TODO 2: Bind them
            //This is where to bind the Views in the item_jentry layout.
            titleView.setText(jEntry.title);
        }


        @Override
        public void onClick(View v) {

            /*
            //In JEGreen adapter the Intent that redirects to the ViewDetail Activity is defined in the
            // MainActivity class
            //In this instance I can do all from here in the Adapter. Is that better?
             This is how the position was passed in the JEntryGreenAdapter
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);*/

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, JEntryDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            mContext.startActivity(intent);
        }
    }

}
