package com.thiti.blocnote;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseOpenHelper mOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureRecyclerView();
        this.configureOpenHelper();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_add:
                add();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Créer un nouveau fichier");
        builder.setMessage("Saisir l nom du fichier");
        builder.setView(getLayoutInflater().inflate(R.layout.dialog_add,null));

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })

    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureRecyclerView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.activity_main_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new NoteAdapter(getLayoutInflater()));
    }

    private void configureOpenHelper(){
        mOpenHelper = new DatabaseOpenHelper(this);
    }

}
