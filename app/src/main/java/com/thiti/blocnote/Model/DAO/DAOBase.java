package com.thiti.blocnote.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.thiti.blocnote.Model.DatabaseOpenHelper;

import java.util.List;

/**
 * DAOBase est la classe mère de toutes les classe DAO, Pour chaque table dans la BDD,
 * on a une classe DAO qui s'occupe de réaliser les instructions CRUD avec la table en question.
 * chacune de ces classe devra dérivée de DAOBase et implémenter les instructions CRUD.
 */
public abstract class DAOBase<T> {

    // numéro de version de la BDD
    protected static final int VERSION = 1;
    // nom de la BDD
    protected static final String NAME = "databaseNote.db";
    // permet de communiquer avec la BDD
    protected SQLiteDatabase mDatabase = null;
    // permet la création/maj de la BDD
    protected DatabaseOpenHelper mOpenHelper = null;

    public SQLiteDatabase getDatabase(){return mDatabase;}

    public DAOBase(Context context) {
        mOpenHelper = new DatabaseOpenHelper(context,NAME,null,VERSION);
    }

    // ouvre une connexion avec la BDD
    public SQLiteDatabase open(){
        mDatabase = mOpenHelper.getWritableDatabase();
        return mDatabase;
    }

    // ferme la connexion avec la BDD
    public void close(){
        mDatabase.close();
    }

    // méthode permettant l'ajout dans la BDD
    public abstract void add(T objet);

    // méthode permettant la supprésion dans la BDD
    public abstract void delete(T objet);

    // méthode permettant de modifier des champs dans la BDD
    public abstract void upgrade(T objet);

    // méthode permettant de trouver un enregistrement grace à son id dans la BDD
    public abstract T find(long id);

    // méthode retournant tout les champs d'une table dans la BDD
    public abstract List<T> all();

}
