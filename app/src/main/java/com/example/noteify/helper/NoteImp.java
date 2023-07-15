package com.example.noteify.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteImp extends SQLiteOpenHelper implements NoteInterface{

    public NoteImp(Context context){
        super(context, "db_note", null, 1);
    }

    @Override
    public Cursor read() {
        SQLiteDatabase sql = getReadableDatabase();
        return sql.rawQuery("SELECT * FROM note", null);
    }

    @Override
    public boolean create(Note note) {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT INTO note VALUES('"+note.getId()+"','"+note.getTitle()+"','"+note.getDesc()+"')");
            return true;
    }

    @Override
    public boolean update(Note note) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL("UPDATE note SET title='"+note.getTitle()+"', description='"+note.getDesc()+"' WHERE id='"+note+"'");
        return true;
    }

    @Override
    public boolean delete(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM note WHERE id='"+id+"'");
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE note (id TEXT, title TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE note");
    }
}
