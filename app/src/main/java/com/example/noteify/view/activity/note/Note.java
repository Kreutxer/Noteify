package com.example.noteify.view.activity.note;

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String title;
    private String description;
    private String userId;
    private String content;

    public Note() {
        // Default constructor required for Parcelable
    }

    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        userId = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(userId);
        dest.writeString(content);
    }
}

/**
 * NIM : 10120069
 * NAMA : Rendy Agustin
 * KELAS : IF-2
 */
