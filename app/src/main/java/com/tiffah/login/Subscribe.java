package com.tiffah.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Subscribe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe2);

        TextView  sub_month = findViewById(R.id.month_sub);
        sub_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MonthlySub.class);
                startActivity(intent);
            }
        });
        TextView sub_week = findViewById(R.id.week_sub);
        sub_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WeeklySub.class);
                startActivity(intent);
            }
        });
        TextView sub_daily = findViewById(R.id.daily_sub);
        sub_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Request.class);
                startActivity(intent);
            }
        });


    }
}