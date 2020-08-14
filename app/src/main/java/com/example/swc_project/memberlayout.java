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

public class memberlayout extends AppCompatActivity {
    private Spinner spn_birth,spn_address,spn_school,spn_subject;
    private static final String TAG = "memberlayout";
    EditText mem_etID,mem_etPW;
    Button bt_member;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_layout);
        mAuth = FirebaseAuth.getInstance();

        spn_birth = (Spinner)findViewById(R.id.spn_birth);
        spn_address = (Spinner)findViewById(R.id.spn_address);
        spn_school = (Spinner)findViewById(R.id.spn_school);
        spn_subject = (Spinner)findViewById(R.id.spn_subject);

        mem_etID = (EditText)findViewById(R.id.mem_etID);
        mem_etPW = (EditText)findViewById(R.id.mem_etPW);
        bt_member = (Button)findViewById(R.id.bt_member);
        final String[] text_birth = new String[1];//스피너값이 배열값이기 때문
        final String[] text_address = new String[1];;
        final String[] text_school = new String[1];;
        final String[] text_subject = new String[1];;
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

        bt_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st_mem_etID = mem_etID.getText().toString();
                String st_mem_etPW = mem_etPW.getText().toString();
                if(st_mem_etID.isEmpty()){
                    Toast.makeText(memberlayout.this,"Please insert Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(st_mem_etPW.isEmpty()){
                    Toast.makeText(memberlayout.this,"Please insert Password",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(st_mem_etID, st_mem_etPW)
                        .addOnCompleteListener(memberlayout.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent in = new Intent(memberlayout.this,MainActivity.class);
                                    startActivity(in);
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(memberlayout.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });







    }
}
