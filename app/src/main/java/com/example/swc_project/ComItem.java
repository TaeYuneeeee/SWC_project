package com.example.swc_project;

public class ComItem {
    private String nickname;
    private String comment;
    private String userId;

    public ComItem(String nickname, String comment, String userId){
        this.nickname = nickname;
        this.comment = comment;
        this.userId = userId;

    }
    public ComItem(){
//이거 꼭 넣어야돼 파이어베이스 이용시에는 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ 몰랐따고!!!!!!!!!!!!
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
