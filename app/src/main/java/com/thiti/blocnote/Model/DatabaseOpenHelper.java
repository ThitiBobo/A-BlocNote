package com.thiti.blocnote.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.thiti.blocnote.Model.DAO.NoteDAO;

/*
    La classe DatabaseOpenHelper permet la création et la mise à jour de la BDD
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {


    public DatabaseOpenHelper(Context context, String name, CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
    }

    // Création de la BDD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NoteDAO.NOTE_TABLE_CREATE);
    }

    // MAJ de la BDD
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NoteDAO.NOTE_TABLE_DROP);
        onCreate(sqLiteDatabase);
    }
}
