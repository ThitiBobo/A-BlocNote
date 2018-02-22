package com.thiti.blocnote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private LayoutInflater mLayoutInflater;

    public NoteAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
