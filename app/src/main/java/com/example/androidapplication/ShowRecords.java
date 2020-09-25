package com.example.androidapplication;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ShowRecords extends AppCompatActivity {

    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);


        actionBar = getSupportActionBar();
        actionBar.setTitle("All Information");

        mRecyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);


        showRecord();

        fab = findViewById(R.id.addFabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowRecords.this, AddRecordActivity.class);
                intent.putExtra("editMode",false);
                startActivity(intent);

            }
        });

    }
//search

    //search

    private void showRecord() {

        Adapter adapter = new Adapter(ShowRecords.this, databaseHelper.getAllData(Constants.C_ADD_NOTICE + " DESC"));
        //last added record will be show on top-record sorting
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

    @Override

    //this funvtion kills all activites.. so
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}