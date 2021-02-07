package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEjer1,btnEjer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEjer1 = findViewById(R.id.btnEjer1);
        btnEjer2 = findViewById(R.id.btnEjer2);
        eventos();
    }

    public void eventos() {
        btnEjer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Ejer1Activity.class);
                startActivity(i);
            }
        });
        btnEjer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Ejer2Activity.class);
                startActivity(i);
            }
        });
    }
}




