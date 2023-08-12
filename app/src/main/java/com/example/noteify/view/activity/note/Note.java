package com.example.noteify.view.activity.note;
import android.os.Parcel;
import android.os.Parcelable;

public class Note {
    private String title;
    private String description;
    private String userId;


    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
