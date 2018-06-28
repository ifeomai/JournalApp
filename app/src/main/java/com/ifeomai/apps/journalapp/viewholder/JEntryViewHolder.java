package com.ifeomai.apps.journalapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.R;
import com.ifeomai.apps.journalapp.database.JEntry;

public class JEntryViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView updatedAtView;
    public TextView descriptionView;



    public JEntryViewHolder(View itemView) {

        super(itemView);
        titleView = itemView.findViewById(R.id.post_title);
        updatedAtView = itemView.findViewById(R.id.post_updated_at);
        descriptionView = itemView.findViewById(R.id.post_description);

    }


    public void bindToPost(JEntry post, View.OnClickListener starClickListener) {
        titleView.setText(post.title);
        descriptionView.setText(post.description);
        updatedAtView.setText(post.updatedAt);

    }
}
