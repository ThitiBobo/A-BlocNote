package com.thiti.blocnote.Controler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiti.blocnote.Model.DAO.DAOBase;
import com.thiti.blocnote.Model.Note;
import com.thiti.blocnote.Model.DAO.NoteDAO;
import com.thiti.blocnote.R;
import com.thiti.blocnote.View.NoteViewHolder;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Note> mNotes = new ArrayList<Note>();
    private DAOBase<Note> mNoteDAO;

    public NoteAdapter(Context context, LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mContext = context;
        this.configureDAO();

        // test code
        mNoteDAO.open();
        List<Note> notes = mNoteDAO.all();

        for(Note n : notes){
            mNotes.add(n);
            notifyDataSetChanged();
        }

        mNoteDAO.close();
        // test code

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

    public void add(String title){
        Note newNote = new Note(title);
        mNotes.add(newNote);

        mNoteDAO.open();
        mNoteDAO.add(newNote);
        mNoteDAO.close();

        notifyDataSetChanged();
    }

    private void configureDAO(){mNoteDAO = new NoteDAO(mContext);}
}
