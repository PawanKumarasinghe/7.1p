package com.example.lostfound;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class StorageHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "lostfound_alt.db";
    private static final int DB_VERSION = 1;

    public StorageHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE posts (id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, name TEXT, contact TEXT, info TEXT, timing TEXT, place TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS posts");
        onCreate(db);
    }

    public void addEntry(String category, String name, String contact, String info, String timing, String place) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", category);
        values.put("name", name);
        values.put("contact", contact);
        values.put("info", info);
        values.put("timing", timing);
        values.put("place", place);
        db.insert("posts", null, values);
    }

    public List<Post> fetchAll() {
        List<Post> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM posts", null);
        while (cursor.moveToNext()) {
            data.add(new Post(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        }
        cursor.close();
        return data;
    }

    public Post getEntry(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM posts WHERE id=?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            Post post = new Post(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
            cursor.close();
            return post;
        }
        cursor.close();
        return null;
    }

    public void removeEntry(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("posts", "id=?", new String[]{String.valueOf(id)});
    }
}

