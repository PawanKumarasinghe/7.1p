package com.example.lostfound;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListScreen extends AppCompatActivity implements PostAdapter.ClickListener {
    RecyclerView postList;
    StorageHelper db;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        postList = findViewById(R.id.postRecycler);
        db = new StorageHelper(this);

        List<Post> allPosts = db.fetchAll();
        adapter = new PostAdapter(allPosts, this);
        postList.setLayoutManager(new LinearLayoutManager(this));
        postList.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(Post post) {
        Intent intent = new Intent(this, DetailScreen.class);
        intent.putExtra("post_id", post.getId());
        startActivity(intent);
    }
}