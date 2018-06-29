
package com.ifeomai.apps.journalapp.database;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class JEntry {
    public String title;
    public String description;
    public String updatedAt;

    public JEntry(){
        //default Constructor
    }
    public JEntry(String title,String description, String updatedAt) {
        //this.owner = owner;
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
    }
    // [START post_to_map]

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description", description);
        result.put("title", title);
        result.put("updatedAt", updatedAt);
        return result;

    }

    // [END post_to_map]

}

/*

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
@Entity(tableName = "entries")
public class JEntry {
        @PrimaryKey(autoGenerate = true)
        //private String id;
        public String title;
        public String description;
        public Date updatedAt;

        @Ignore
        public JEntry(String description,String title, Date updatedAt) {
            //this.owner = owner;
            this.title = title;
            this.description = description;
            this.updatedAt = updatedAt;
        }

        public JEntry(String id, String description, String title, Date updatedAt) {
            this.id = id;
            //this.owner = owner;
            this.title = title;
            this.description = description;
            this.updatedAt = updatedAt;
        }

        */
/*public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   *//*
*/
/* public String getOwner() {
            return this.owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }*//*
*/
/*

        public Date getUpdatedAt() {
            return updatedAt;
        }
        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

*//*


}
*/
