package com.example.androidapplication;

//IT19118246
//Wijesekera S.M
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

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ListCarViewCustomer extends AppCompatActivity {

    private static final String CHANNEL_ID ="channel2" ;
    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;

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

                Intent intent = new Intent(ListCarViewCustomer.this, ShowRecords.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(ListCarViewCustomer.this, 0, intent, 0);
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(ListCarViewCustomer.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("-CarMart-")
                        .setContentText("You Can Add a wish to buy Notice from Here....")
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

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }
}

