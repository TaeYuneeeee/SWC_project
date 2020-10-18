package com.example.swc_project;
public class RecyItem {
    private String uid;
    private String body;
    private String title;
    private String key;
    private int starCount;



    public RecyItem(String uid, String body, String title, String key){
        this.uid = uid;
        this.body = body;
        this.title = title;
        this.key = key;

    }
    public RecyItem(){
//이거 꼭 넣어야돼 파이어베이스 이용시에는 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ 몰랐따고!!!!!!!!!!!!
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getbody() {
        return body;
    }

    public void setbody(String body) {
        this.body = body;
    }

    public String getkey() {
        return key;
    }

    public void setkey(String key) {
        this.key = key;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
