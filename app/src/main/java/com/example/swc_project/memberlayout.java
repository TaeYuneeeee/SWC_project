package com.example.swc_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class memberlayout extends AppCompatActivity {
    private Spinner spn_birth,spn_address,spn_school,spn_subject;
    private DatabaseReference mDatabase;
//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText et_mem_lay;
    Button bt_member;
    TextView test1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_layout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        bt_member = (Button)findViewById(R.id.bt_member);
        spn_birth = (Spinner)findViewById(R.id.spn_birth);
        spn_address = (Spinner)findViewById(R.id.spn_address);
        spn_school = (Spinner)findViewById(R.id.spn_school);
        spn_subject = (Spinner)findViewById(R.id.spn_subject);
        et_mem_lay = (EditText)findViewById(R.id.et_mem_lay);
        test1 = (TextView)findViewById(R.id.text1);
        final String[] text_birth = new String[1];//스피너값이 배열값이기 때문
        final String[] text_address = new String[1];;
        final String[] text_school = new String[1];;
        final String[] text_subject = new String[1];;

        final String[] nickname = new String[1];
               nickname[0] = et_mem_lay.getText().toString();

        spn_birth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text_birth[0] = spn_birth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            text_address[0] = spn_address.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text_school[0] = spn_school.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text_subject[0] = spn_subject.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String test = user.getUid(); // user 아이디 갖고옴 이걸 토대로 분류분류하면될듯
        test1.setText(test);
        bt_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(memberlayout.this,"입력",Toast.LENGTH_LONG).show();
                writeNewPost(text_birth[0],text_address[0],text_school[0],text_subject[0],nickname[0]);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("User");
//                myRef.setValue("Hello, World!");
//파이어베이스 입력할때 AVD 구동을 다시시작 해보고 하기
            }
        });


    }
//아이디당 한번만 입력 후에는 업데이트 방식
    private void writeNewPost(String birth, String address, String school, String subject, String nickname) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("user-infor").push().getKey();
        userdata post = new userdata(birth, address, school, subject,nickname);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}
