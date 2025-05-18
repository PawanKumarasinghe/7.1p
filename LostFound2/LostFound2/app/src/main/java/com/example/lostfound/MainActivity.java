package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button newPostBtn, viewItemsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPostBtn = findViewById(R.id.btnNewPost);
        viewItemsBtn = findViewById(R.id.btnViewItems);

        newPostBtn.setOnClickListener(view -> startActivity(new Intent(this, PostFormScreen.class)));
        viewItemsBtn.setOnClickListener(view -> startActivity(new Intent(this, ListScreen.class)));
    }
}