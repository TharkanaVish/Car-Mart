package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Homepage extends AppCompatActivity {
    Button notices;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        notices = findViewById(R.id.btnnotices);

    super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);

        Button brandNewBtn = findViewById(R.id.btnBrandNew);
        Button notices  = findViewById(R.id.btnnotices);


        brandNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this,BrandNewForm.class);
                startActivity(i);
            }
        });



        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,ShowRecords.class));

            }
        });



    }
}


