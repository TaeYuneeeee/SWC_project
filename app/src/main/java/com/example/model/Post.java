package com.example.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {
    public String uid;
//    public String author;
    public String title;
    public String body;
    public String key;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String title, String body, String key) {
        this.uid = uid;
//        this.author = author;
        this.title = title;
        this.body = body;
        this.key = key;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
//        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("key", key);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

    // [END post_to_map]
}
