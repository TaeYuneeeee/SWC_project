package com.example.model;

public class User { //dataSnapshot용
//        public String username;
        public String nickname;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String nickname) {
//            this.username = username;
            this.nickname = nickname;
        }
}
