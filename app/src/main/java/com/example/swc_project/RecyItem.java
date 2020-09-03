package com.example.swc_project;
public class RecyItem {
//    private Drawable iconDraWable;
    private String title;
    private String body;
    private String postNumStars;

//    public Drawable getIconDraWable() {
//        return iconDraWable;
//    }
//
//    public void setIconDraWable(Drawable iconDraWable) {
//        this.iconDraWable = iconDraWable;
//    }
    public RecyItem(String title, String body){
        this.title = title;
        this.body = body;
//        this.postNumStars = postNumStars;
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

//    public String getPostNumStars() {
//        return postNumStars;
//    }
//
//    public void setPostNumStars(String postNumStars) {
//        this.postNumStars = postNumStars;
//    }
}
