package com.thiti.blocnote.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thiti.blocnote.Model.Note;
import com.thiti.blocnote.NoteActivity;
import com.thiti.blocnote.R;


public class NoteViewHolder extends RecyclerView.ViewHolder {

    private CardView mCardView;
    private TextView mTextViewTitel;
    private TextView mTextViewContent;

    private Context mContext;

    private Note mNote;

    public NoteViewHolder(Context context, View itemView) {

        super(itemView);

        mContext = context;
        mCardView = (CardView)itemView.findViewById(R.id.item_note_cardview);
        mTextViewTitel = (TextView)itemView.findViewById(R.id.item_note_text_title);
        mTextViewContent = (TextView)itemView.findViewById(R.id.item_note_text_content);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteActivity = new Intent(mContext, NoteActivity.class);
                noteActivity.putExtra("IdNote",String.valueOf(mNote.getId()));
                mContext.startActivity(noteActivity);
            }
        });
    }

    public void bind(Note note){
        mNote = note;
        mTextViewTitel.setText(note.getTitle());
        mTextViewContent.setText(note.getContent());
    }
}
