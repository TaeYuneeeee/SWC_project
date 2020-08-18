package com.example.swc_project;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class userdata {
        public String birth;
        public String address;
        public String school;
        public String subject;

        public userdata(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public userdata(String birth, String address, String school, String subject) {
            this.birth = birth;
            this.address =address;
            this.school = school;
            this.subject = subject;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("birth", birth);
            result.put("address", address);
            result.put("school", school);
            result.put("subject", subject);
            return result;
        }
    }
