package com.example.swc_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register  extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "register";
    EditText regt_etID,regt_etPW;
    Button bt_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        mAuth = FirebaseAuth.getInstance();

        regt_etID = (EditText)findViewById(R.id.regt_etID);
        regt_etPW = (EditText)findViewById(R.id.regt_etPW);
        bt_register = (Button)findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st_regt_etID = regt_etID.getText().toString();
                String st_regt_etPW = regt_etPW.getText().toString();

                if(st_regt_etID.isEmpty()){
                    Toast.makeText(register.this,"Please insert Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(st_regt_etPW.isEmpty()){
                    Toast.makeText(register.this,"Please insert Password",Toast.LENGTH_LONG).show();
                    return;
                }
                User user = new User(st_regt_etID);
                mAuth.createUserWithEmailAndPassword(st_regt_etID, st_regt_etPW)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent in = new Intent(register.this,MainActivity.class);
                                    startActivity(in);
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(register.this, "Authentication failed.",
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
