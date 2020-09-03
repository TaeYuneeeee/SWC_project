package com.example.swc_project;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    ViewGroup viewGroup;
    private static final String TAG = "PostListFragment";

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecyItem> mMyData;
//    private ArrayList<Post> mMyData;

    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        mRecyclerView = (RecyclerView) view.findViewById(R.id.Recy);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new Adapter(mMyData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyData = new ArrayList<>();
//        initDataset();
        //databaseReference = database.getReference("posts"); // DB 테이블 연결
        databaseReference = database.getInstance().getReference("posts"); // DB 테이블 연결
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts);
        //databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                mMyData.clear(); // 기존 배열리스트가 존재하지않게 초기화
/*                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    RecyItem item = snapshot.getValue(RecyItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
//                    mMyData.add(item); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    mMyData.add(snapshot.getValue(RecyItem.class));
                }*/
/*                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    RecyItem item = ds.getValue(RecyItem.class);
                    mMyData.add(item);
                }*/
//                Post post = dataSnapshot.getValue(Post.class);
                mAdapter.notifyDataSetChanged();; // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("Fragment3", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        return view;


    }


    private void initDataset() {
        //for Test
        mMyData.clear();
        mMyData.add(new RecyItem("test","test1"));
    }



}