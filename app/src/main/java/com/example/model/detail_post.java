package com.example.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class detail_post {
//    public String uid;
    public String nickname;
    public String comment;
    public String userId;

    public Map<String, Boolean> stars = new HashMap<>();

    public detail_post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public detail_post(String nickname, String comment, String userId) {
//        this.uid = uid;
        this.nickname = nickname;
        this.comment = comment;
        this.userId = userId;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
        result.put("nickname", nickname);
        result.put("comment", comment);
        result.put("userId", userId);
        return result;
    }

    // [END post_to_map]
}
