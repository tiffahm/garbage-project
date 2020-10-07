package com.tiffah.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    RelativeLayout cb = findViewById(R.id.cleaningbox);

    cb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),CleaningActivity.class);
            startActivity(intent);
        }
    });
        RelativeLayout gb = findViewById(R.id.collectionbox);
        gb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Subscribe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}