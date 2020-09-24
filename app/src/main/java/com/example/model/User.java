package com.example.model;

public class User { //dataSnapshot용

        private String nickname;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String nickname) {
//            this.username = username;
            this.nickname = nickname;
        }

        public String getNickname() {
                return nickname;
        }

        public void setNickname(String nickname) {
                this.nickname = nickname;
        }
}
