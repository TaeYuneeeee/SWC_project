package com.example.swc_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class memberlayout extends AppCompatActivity {
    private Spinner spn_birth,spn_address,spn_school,spn_subject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_layout);
        final TextView text1,text2,text3,text4;
        spn_birth = (Spinner)findViewById(R.id.spn_birth);
        spn_address = (Spinner)findViewById(R.id.spn_address);
        spn_school = (Spinner)findViewById(R.id.spn_school);
        spn_subject = (Spinner)findViewById(R.id.spn_subject);
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
    }
}
