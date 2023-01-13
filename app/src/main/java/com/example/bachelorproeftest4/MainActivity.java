package com.example.bachelorproeftest4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<String> itemlist = new ArrayList<>();
    static ArrayAdapter<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemlist.add("example one");
        itemlist.add("example two");


        ListView listview = findViewById(R.id.list_view);
        arr = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, itemlist);
        listview.setAdapter(arr);

        listview.setClickable(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), activity_edit_note.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        FloatingActionButton addBtn = findViewById(R.id.floating_add_button);
        addBtn.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_create_note.class);
                startActivity(intent);
            }
        });
    }
}