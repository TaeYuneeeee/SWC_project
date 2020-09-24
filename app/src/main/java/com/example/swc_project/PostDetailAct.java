package com.example.swc_project;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.detail_post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostDetailAct extends AppCompatActivity {
    ViewGroup viewGroup;
    private static final String TAG = "PostDetailAct";

    private RecyclerView mRecyclerView;
    private dapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ComItem> mMyData;

    private Context context;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    private DatabaseReference mDatabase;
    String title;
    String body;
    final String userId = "Abc";
    TextView tv1;
    TextView tv2;
    EditText edt1;
    Button bt1;

    static String nickname = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        mRecyclerView = (RecyclerView) findViewById(R.id.detail_Recy);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.scrollToPosition(0);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tv1 = (TextView)findViewById(R.id.detail_postTitle);
        tv2 = (TextView)findViewById(R.id.detail_postBody);
        bt1 = (Button)findViewById(R.id.detail_bt);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAdapter = new dapter(mMyData);
        mMyData = new ArrayList<>();
        this.title = getIntent().getStringExtra("title");
        this.body = getIntent().getStringExtra("body");
        tv1.setText(title);
        tv2.setText(body);
        final String key = getIntent().getStringExtra("key");

        edt1 = (EditText)findViewById(R.id.detail_et);
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user1.getUid(); // user 아이디 갖고옴 이걸 토대로 분류분류하면될
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("posts").child(key).child("Commnets");
        bt1.setOnClickListener(new View.OnClickListener() {//버튼클릭시 코멘트입력
            @Override
            public void onClick(View view) {
                String commentText = edt1.getText().toString();
                nickname = "testcheck";
                detail_post dpost = new detail_post(nickname,commentText,userId);
                databaseReference1.push().setValue(dpost);
            }

        });

        /*
        * 닉네임 가져오기가 필요함 시부엉
        * *//*
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("memberinfor");
        mDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User udata = dataSnapshot.getValue(User.class);
                nickname = udata.getNickname();
                Log.d(TAG,"nickname : "+nickname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts").child(key).child("Commnets"); // DB 테이블 연결z
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                mMyData.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ComItem Item = snapshot.getValue(ComItem.class);
                    Log.d(TAG,"check : "+Item.getComment());
                    Log.d(TAG,"check : "+Item.getNickname());
                    mMyData.add(Item); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                mAdapter.notifyDataSetChanged();; // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e(TAG, String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }



}
