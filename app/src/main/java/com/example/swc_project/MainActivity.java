package com.example.swc_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etId, etPassword;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mAuth = FirebaseAuth.getInstance();

        Button btLogin = (Button)findViewById(R.id.btLogin);
        Button btMember = (Button)findViewById(R.id.btMember);
        etId = (EditText)findViewById(R.id.etID);
        etPassword = (EditText)findViewById(R.id.etPW);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stId = etId.getText().toString();
                String stPw = etPassword.getText().toString();

                if(stId.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please insert Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(stPw.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please insert Password",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(stId, stPw)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                                    Intent in = new Intent(MainActivity.this,mainlayout.class);
                                    startActivity(in);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }

                                // ...
                            }
                        });


            }
        });
        btMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,register.class);
                startActivity(in);
            }
        });
    }
}