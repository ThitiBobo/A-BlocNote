package com.thiti.blocnote.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.thiti.blocnote.Model.DatabaseOpenHelper;

import java.util.List;

/**
 * Created by canard on 23/02/18.
 */

public abstract class DAOBase<T> {

    protected static final int VERSION = 1;
    protected static final String NAME = "databaseNote.db";

    protected SQLiteDatabase mDatabase = null;
    protected DatabaseOpenHelper mOpenHelper = null;

    public SQLiteDatabase getDatabase(){return mDatabase;}

    public DAOBase(Context context) {
        mOpenHelper = new DatabaseOpenHelper(context,NAME,null,VERSION);
    }

    public SQLiteDatabase open(){
        mDatabase = mOpenHelper.getWritableDatabase();
        return mDatabase;
    }

    public void close(){
        mDatabase.close();
    }

    public abstract void add(T objet);

    public abstract void delete(T objet);

    public abstract void upgrade(T objet);

    public abstract T find(long id);

    public abstract List<T> all();

}
