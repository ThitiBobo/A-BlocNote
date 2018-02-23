package com.thiti.blocnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

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


    public DatabaseOpenHelper(Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NOTE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NOTE_TABLE_DROP);
        onCreate(sqLiteDatabase);
    }
}
