package com.example.bachelorproeftest4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class activity_create_note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        EditText editText = findViewById(R.id.textView);

        Button saveBtn = findViewById(R.id.savebtn);

        saveBtn.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                MainActivity.itemlist.add(text);
                MainActivity.arr.notifyDataSetChanged();
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.itemlist);
                sharedPreferences.edit().putStringSet("notes", set).apply();

                finish();
            }
        });
    }
}