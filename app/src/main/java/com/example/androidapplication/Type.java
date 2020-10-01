package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Type extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        btn1 = (Button)findViewById(R.id.button3);
        btn2 = (Button)findViewById(R.id.button4);
        btn3 = (Button)findViewById(R.id.button5);
        btn4 = (Button)findViewById(R.id.button6);


        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Type.this,BrandNewForm.class);
                        startActivity(i);
                    }
                }
        );

        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Type.this,BrandNewForm.class);
                        startActivity(i);
                    }
                }
        );

        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Type.this,BrandNewForm.class);
                        startActivity(i);
                    }
                }
        );

        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Type.this,BrandNewForm.class);
                        startActivity(i);
                    }
                }
        );
    }
}