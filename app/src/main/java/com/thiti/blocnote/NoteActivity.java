package com.thiti.blocnote;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.thiti.blocnote.Model.DAO.DAOBase;
import com.thiti.blocnote.Model.DAO.NoteDAO;
import com.thiti.blocnote.Model.Note;

public class NoteActivity extends AppCompatActivity {

    private EditText mContent;
    private Note mNote;
    private DAOBase<Note> mNoteDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mContent = (EditText)findViewById(R.id.activity_note_edit_content);

        this.configureDAO();
        this.configureNote();
        this.configureToolbar();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            saveDialog();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_note_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_save:
                save();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        saveDialog();
        return true;
    }

    private void save(){
        mNote.setContent(mContent.getText().toString());
        mNoteDao.open();
        mNoteDao.upgrade(mNote);
        mNoteDao.close();
    }

    private void saveDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enregistrer vos modification ou annuler ?");
        builder.setView(getLayoutInflater().inflate(R.layout.dialog_save,null));
        builder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("Ignorer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                save();
                finish();
            }
        });
        builder.show();
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mNote.getTitle());
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    private void configureDAO(){mNoteDao = new NoteDAO(this);}

    private void configureNote(){
        Intent intent = getIntent();
        long id = Long.valueOf(intent.getStringExtra("IdNote"));
        mNoteDao.open();
        mNote = mNoteDao.find(id);
        mNoteDao.close();

        mContent.setText(mNote.getContent());
    }
}
