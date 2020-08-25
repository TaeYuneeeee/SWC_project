package com.example.swc_project;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class userdata {
        public String birth;
        public String address;
        public String school;
        public String subject;
        public String nickname;

        public userdata(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public userdata(String birth, String address, String school, String subject, String nickname) {
            this.birth = birth;
            this.address =address;
            this.school = school;
            this.subject = subject;
            this.nickname = nickname;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("birth", birth);
            result.put("address", address);
            result.put("school", school);
            result.put("subject", subject);
            result.put("nickname", nickname);
            return result;
        }
    }
