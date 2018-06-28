package com.ifeomai.apps.journalapp.database;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.R;

import java.util.List;

public class JEntryAdapter extends ArrayAdapter<JEntry> {
    public  JEntryAdapter(Context context, int resource, List<JEntry> objects) {
        super(context,resource,objects);
    }

   @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_jentry, parent, false);
        }
       TextView titleView = (TextView) convertView.findViewById(R.id.post_title);
       TextView updatedAtView = (TextView) convertView.findViewById(R.id.post_updated_at);
       TextView descriptionView = (TextView) convertView.findViewById(R.id.post_description);
       JEntry jEntry= getItem(position);

       titleView.setText(jEntry.title);
       updatedAtView.setText(jEntry.updatedAt);
       descriptionView.setText(jEntry.description);

       return convertView;
   }
}
