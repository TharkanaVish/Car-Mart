package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Homepage extends AppCompatActivity {
    Button brandNewBtn;
    Button notices;
    Button buyCar;
    Button  sellcar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        brandNewBtn = findViewById(R.id.btnBrandNew);
        notices = findViewById(R.id.btnnotices);
        buyCar = findViewById(R.id.button12);
        sellcar= findViewById(R.id.button13);


    super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);

        Button brandNewBtn = findViewById(R.id.btnBrandNew);
        Button notices  = findViewById(R.id.btnnotices);
        Button buyCar = findViewById(R.id.button12);
        Button sellcar= findViewById(R.id.button13);


        brandNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this,BrandNewForm.class);
                startActivity(i);
            }
        });

        buyCar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent (Homepage.this,ListCarViewCustomer.class));


            }
        });


        sellcar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent (Homepage.this,ListCarView.class));


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


