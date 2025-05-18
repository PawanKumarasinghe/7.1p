package com.example.lostfound;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailScreen extends AppCompatActivity {
    TextView postContent;
    Button deleteBtn;
    StorageHelper db;
    int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        postContent = findViewById(R.id.viewContent);
        deleteBtn = findViewById(R.id.btnDelete);
        db = new StorageHelper(this);

        postId = getIntent().getIntExtra("post_id", -1);
        Post post = db.getEntry(postId);
        postContent.setText(post.getFormatted());

        deleteBtn.setOnClickListener(view -> {
            db.removeEntry(postId);
            finish();
        });
    }
}