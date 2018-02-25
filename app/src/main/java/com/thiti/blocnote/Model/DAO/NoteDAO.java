package com.thiti.blocnote.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.thiti.blocnote.Model.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteDAO extends DAOBase<Note> {

    public static final String NOTE_TABLE_NAME = "NOTE";

    public static final String NOTE_ID = "ID";
    public static final String NOTE_TITLE = "TITLE";
    public static final String NOTE_CONTENT = "CONTENT";

    public static final String NOTE_TABLE_CREATE =
            "CREATE TABLE " + NOTE_TABLE_NAME + " ("
                    + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NOTE_TITLE + " TEXT,"
                    + NOTE_CONTENT + " TEXT);";

    public static final String NOTE_TABLE_DROP = "DROP TABLE IF EXIST " + NOTE_TABLE_NAME + ";";


    public NoteDAO(Context context) {
        super(context);
    }

    @Override
    public void add(Note note) {
        ContentValues value = new ContentValues();
        value.put(NOTE_TITLE, note.getTitle());
        value.put(NOTE_CONTENT,note.getContent());
        note.setId(mDatabase.insert(NOTE_TABLE_NAME,null,value));
    }

    @Override
    public void delete(Note note) {
        mDatabase.delete(NOTE_TABLE_NAME,NOTE_ID + " = ?",new String[]{String.valueOf(note.getId())});
    }

    @Override
    public void upgrade(Note note) {
        ContentValues value = new ContentValues();
        value.put(NOTE_TITLE,note.getTitle());
        value.put(NOTE_CONTENT,note.getContent());
        mDatabase.update(NOTE_TABLE_NAME, value, NOTE_ID + " = ?", new String[]{String.valueOf(note.getId())});
    }

    @Override
    public Note find(int id) {
        Cursor cursor = mDatabase.query(
                NOTE_TABLE_NAME,
                new String[]{String.valueOf(NOTE_ID),NOTE_TITLE,NOTE_CONTENT},
                NOTE_ID + " = ?",
                new String[]{String.valueOf(NOTE_ID)},
                null,
                null,
                null,
                null
                );

        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
        // manque cursor.close (regarder plus infos sur cursor)
    }

    @Override
    public List<Note> all() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + NOTE_TABLE_NAME,null);

        List<Note> notes = new ArrayList<Note>();

        if (cursor == null)
            return new ArrayList<Note>();

        while (cursor.moveToNext()) {
            notes.add(new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
        }

        cursor.close();
        return notes;
    }
}
