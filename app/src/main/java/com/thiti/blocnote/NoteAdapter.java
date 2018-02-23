package com.thiti.blocnote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Note> mNotes = new ArrayList<Note>();

    public NoteAdapter(Context context, LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mContext = context;
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
        mNotes.add(new Note(title));
        notifyDataSetChanged();
    }
}
