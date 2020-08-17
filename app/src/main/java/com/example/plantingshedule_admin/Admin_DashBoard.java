package com.example.plantingshedule_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.admin_t1);
        textView1.setText(R.string.admintext1);

        Button btnadd = findViewById(R.id.btn_d);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewCrops.class);
                startActivity(intent);
            }
        });

        Button btnupdate = findViewById(R.id.btn_u);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateShedule.class);
                startActivity(intent);
            }
        });

        Button btndel = findViewById(R.id.btn_del);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PlantSheduleDeletion.class);
                startActivity(intent);
            }
        });


    }
}