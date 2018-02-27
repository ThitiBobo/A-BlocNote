package com.thiti.blocnote;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.thiti.blocnote.Model.DAO.DAOBase;
import com.thiti.blocnote.Model.DAO.NoteDAO;
import com.thiti.blocnote.Model.Note;

public class NoteActivity extends AppCompatActivity {

    private TextView mTitle;
    private EditText mContent;

    private Note mNote;

    private DAOBase<Note> mNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        this.configureDAO();

        mTitle = (TextView)findViewById(R.id.activity_note_textview_title);
        mContent = (EditText)findViewById(R.id.activity_note_edit_content);

        Intent intent = getIntent();
        long id = Long.valueOf(intent.getStringExtra("IdNote"));
        mNoteDao.open();
        Log.v("test",String.valueOf(id));
        mNote = mNoteDao.find(id);
        mNoteDao.close();

        mTitle.setText(mNote.getTitle());
        mContent.setText(mNote.getContent());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        mNote.setContent(mContent.getText().toString());
        Log.v("test", String.valueOf(mNote.getContent()));
        mNoteDao.open();
        mNoteDao.upgrade(mNote);
        mNoteDao.close();
    }

    private void configureDAO(){mNoteDao = new NoteDAO(this);}


}
