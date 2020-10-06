package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


public class ListCarViewCustomer extends AppCompatActivity {

    private static final String CHANNEL_ID ="channel2" ;
    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;
    AdapterC recyclerAdapter;
    List<ModelC> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car_view_customer);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Cars For Sale");



        mRecyclerView = findViewById(R.id.recyclerView);

        databaseHelper = new DatabaseHelper(this);
        showRecord();

        fab = findViewById(R.id.addFabButton);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notificationMsg ="Hello Welcome to the MAD team";


                Intent intent = new Intent(ListCarViewCustomer.this, AddNewCar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(ListCarViewCustomer.this, 0, intent, 0);
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(ListCarViewCustomer.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("-CarMart-")
                        .setContentText("Add a wish to buy notice")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ListCarViewCustomer.this);

                notificationManager.notify(0, builder.build());

                intent.putExtra("editMode",false);
                startActivity(intent);

            }
        });

    }



    private void showRecord() {

        AdapterC adapter = new AdapterC(ListCarViewCustomer.this, databaseHelper.getAllCarData(ConstantsC.C_ADD_CAR + " DESC"),false);
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

