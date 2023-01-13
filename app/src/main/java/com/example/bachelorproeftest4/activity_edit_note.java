package com.example.bachelorproeftest4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class activity_edit_note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);


        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();

        int noteId = intent.getIntExtra("noteId", -1);
        if(noteId != -1){
            editText.setText(MainActivity.itemlist.get(noteId));
        }

        Button deleteBtn = findViewById(R.id.delete_button);
        Button saveBtn = findViewById(R.id.save_button);

        saveBtn.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence charSequence = editText.getText();
                MainActivity.itemlist.set(noteId, String.valueOf(charSequence));
                MainActivity.arr.notifyDataSetChanged();
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.itemlist);
                sharedPreferences.edit().putStringSet("notes", set).apply();

                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forsureDelete(noteId);

            }

        });


    }

    public void forsureDelete(int noteId){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete this note?")
                .setTitle("Delete?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // START THE GAME!
                        MainActivity.itemlist.remove(noteId);
                        MainActivity.arr.notifyDataSetChanged();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet(MainActivity.itemlist);
                        sharedPreferences.edit().putStringSet("notes", set).apply();

                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}