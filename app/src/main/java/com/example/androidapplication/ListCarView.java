package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListCarView extends AppCompatActivity {

    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car_view);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Added Car List");

        mRecyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);


        showRecord();

        fab = findViewById(R.id.addFabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListCarView.this, AddNewCar.class);
                intent.putExtra("editMode",false);
                startActivity(intent);

            }
        });

    }


    private void showRecord() {

        AdapterC adapter = new AdapterC(ListCarView.this, databaseHelper.getAllCarData(ConstantsC.C_ADD_CAR + " DESC"),true);

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

    @Override


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
