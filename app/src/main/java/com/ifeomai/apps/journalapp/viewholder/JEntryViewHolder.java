package com.ifeomai.apps.journalapp.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.R;
import com.ifeomai.apps.journalapp.database.DateConverter;
import com.ifeomai.apps.journalapp.database.JEntry;

public class JEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public View mView;
    private Context mContext;



    public JEntryViewHolder(View itemView) {

        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindJEntry(JEntry item){
        TextView titleView = mView.findViewById(R.id.post_title);
        TextView updatedAtView = mView.findViewById(R.id.post_updated_at);
        TextView descriptionView = mView.findViewById(R.id.post_description);

        titleView.setText(item.title);
        descriptionView.setText(item.description);
        updatedAtView.setText(DateConverter.toCustomFormat(item.updatedAt));

    }
    @Override
    public void onClick(View v) {
        //This handles an item click in the View
    }
}
