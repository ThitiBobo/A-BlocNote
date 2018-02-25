package com.thiti.blocnote.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thiti.blocnote.Model.Note;
import com.thiti.blocnote.R;


public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextViewTitel;
    private TextView mTextViewContent;

    public NoteViewHolder(Context context, View itemView) {

        super(itemView);

        mTextViewTitel = (TextView)itemView.findViewById(R.id.item_note_text_title);
        mTextViewContent = (TextView)itemView.findViewById(R.id.item_note_text_content);
    }

    public void bind(Note note){
        mTextViewTitel.setText(note.getTitle());
        mTextViewContent.setText(note.getContent());
    }
}