package com.example.model;

public class User { //dataSnapshot용
//        public String username;
        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String email) {
//            this.username = username;
            this.email = email;
        }
}
