package com.ifeomai.apps.journalapp.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.R;

//Adapters are tied to RecyclerViews and basically Override these 3 methods
public class JEntryGreenAdapter extends RecyclerView.Adapter<JEntryGreenAdapter.JEntryViewHolder> {

    private static final String TAG = JEntryGreenAdapter.class.getSimpleName();
    // # of items this adapter will hold
    private int mNumberItems;


    public JEntryGreenAdapter(int numberOfItems){
        mNumberItems = numberOfItems;
    }

    //Here it creates the viewholder using a layout(static/dynamic) and inflates them
    //This is called several times to fill & scroll the screen or display all items which ever is smaller
    @Override
    public JEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_jentry;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        JEntryViewHolder viewHolder = new JEntryViewHolder(view);

        return viewHolder;
    }

/*   * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * This is where the adapter populates the View with data from our model*/
    @Override
    public void onBindViewHolder(@NonNull JEntryViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    //return the number of items available for display
    @Override
    public int getItemCount(){
        return mNumberItems;
    }


    //*******************************************
    //Cache of the children Views for a list item
    // This looks like a duplication of my viewHolder.JEntryViewHolder
    //*******************************************
    class JEntryViewHolder extends RecyclerView.ViewHolder{
        private TextView titleView;
        private TextView updatedAtView;
        private TextView descriptionView;

        public JEntryViewHolder(View itemView){
            super(itemView);
            titleView = itemView.findViewById(R.id.post_title);
        }
        void bind(int listIndex)
        {
            //TODO: This is where to bind the Views in the item_jentry layout.
            //Q: How do I pass a JEntry object?
            //Replace listIndex with it?
            titleView.setText(String.valueOf(listIndex));
        }

    }


}
