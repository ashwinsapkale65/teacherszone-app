package com.example.teacherszone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    Button logout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

        System.exit(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.logout);

         firebaseAuth = FirebaseAuth.getInstance();
         firebaseUser = firebaseAuth.getCurrentUser();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);


            }
        });

    }

    public void process(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String id = firebaseUser.getUid();
        Bundle ePzl= new Bundle();
        ePzl.putString("key",id);

        Intent i = new Intent(Home.this,Students.class);
        i.putExtras(ePzl);
        startActivity(i);


    }

    public void viewinfo(View view) {
        startActivity(new Intent(Home.this,Sinfo.class));



    }

    public void attend(View view) {
        startActivity(new Intent(Home.this,attendence.class));
    }


    public void viewattendence(View view) {
        startActivity(new Intent(Home.this,attendenceview.class));
    }

    public void demo(View view) {
        startActivity(new Intent(Home.this,demo.class));
    }
}