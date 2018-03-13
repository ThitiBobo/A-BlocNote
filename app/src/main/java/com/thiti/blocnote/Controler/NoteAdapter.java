package com.thiti.blocnote.Controler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiti.blocnote.Model.DAO.DAOBase;
import com.thiti.blocnote.Model.Note;
import com.thiti.blocnote.Model.DAO.NoteDAO;
import com.thiti.blocnote.NoteActivity;
import com.thiti.blocnote.R;
import com.thiti.blocnote.View.NoteViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * NoteAdapter dérive de RecyclerView.Adapter et permet la gestion de tout les éléments Note dans le
 * RecyclerView. Elle garde toute les notes dans une listes et s'occupe de faire le lien avec la BDD
 * en passant par NoteDAO. Elle passe par NoteViewHolder pour gérer l'affichage des Notes.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Note> mNotes = new ArrayList<Note>();
    private DAOBase<Note> mNoteDAO;
    public NoteAdapter(Context context, LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mContext = context;
        this.configureDAO();
        this.loadAllNote();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(mContext,itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    // cette méthode s'occupe d'ajouter une nouvelle note dans la BDD
    // et l'affiche dans le RecyclerView.
    public void add(String title){
        Note newNote = new Note(title);
        mNotes.add(newNote);

        mNoteDAO.open();
        mNoteDAO.add(newNote);
        mNoteDAO.close();

        Intent noteActivity = new Intent(mContext, NoteActivity.class);
        noteActivity.putExtra("IdNote",String.valueOf(newNote.getId()));
        mContext.startActivity(noteActivity);

        notifyDataSetChanged();
    }

    // cette méthode s'occupe de charger toutes les notes enregistrées dans la BDD
    // et les affichent dans le RecyclerView.
    public void loadAllNote(){
        mNotes.clear();
        mNoteDAO.open();
        List<Note> notes = mNoteDAO.all();

        for(Note n : notes){
            mNotes.add(n);
            notifyDataSetChanged();
        }
        mNoteDAO.close();
    }

    // configure l'objet NoteDAO
    private void configureDAO(){mNoteDAO = new NoteDAO(mContext);}
}
