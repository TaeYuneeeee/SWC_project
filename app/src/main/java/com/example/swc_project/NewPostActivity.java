package com.example.swc_project;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.model.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import com.google.firebase.quickstart.database.databinding.ActivityNewPostBinding;
//import com.google.firebase.quickstart.database.java.models.Post;
//import com.google.firebase.quickstart.database.java.models.User;

public class NewPostActivity extends BaseActivity {
    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";
    private ArrayList<RecyItem> mMyData;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    FloatingActionButton fab_check;
    EditText fieldTitle ;
    EditText fieldBody;
    RecyItem recyItem;
    @Override
    protected void onCreate(Bundle savedInstanceStace){
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_new_post);
        mMyData = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fab_check = (FloatingActionButton)findViewById(R.id.fabSubmitPost);
        fieldTitle = (EditText)findViewById(R.id.fieldTitle);
        fieldBody = (EditText)findViewById(R.id.fieldBody);

        fab_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String title = fieldTitle.getText().toString();
                final String body = fieldBody.getText().toString();
//                initDataset(title,body);
                submitPost(title,body);

            }
        });
    }

    private void submitPost(String title1, String body1){

        final String title = title1;
        final String body = body1;
        if(TextUtils.isEmpty(title)){
            Log.d(TAG,"title empty");
            return ;
        }
        if(TextUtils.isEmpty(body)){
            Log.d(TAG,"body empty");
            return ;
        }
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);//이거랑 연관된 놈을 가져오는 것 같습니다.
                        //user클래스로 하나 만들엇으면 거기서 만든 애를 댈꼬오는거지
                        //만들애로 델꼬오는거라서 User를 나중에 id갖고오거나 할려면 쓰고 지금은 nickname으로 쓸거라 그냥 함
//                        if(user == null){
//                            Log.e(TAG,"User "+userId+" is unexpectedly null");
//                            Toast.makeText(NewPostActivity.this,
//                                    "Error: could not fetch user.",
//                                    Toast.LENGTH_LONG).show();
//                        }else{
//                            writeNewPost(userId, user.nickname, title, body);
//                        }
                        //위에꺼 user 어떻게 넣을지가 문제임 DB이쁘장하게 구성하고싶은데 겁나 헷갈림 좀더 개발한후 하든
                        //갑자기 끌릴때 하든 해야될듯합니당

                        writeNewPost(userId, title, body);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });


    }
    private void writeNewPost(String userId, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, title, body, key);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }



}
