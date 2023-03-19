package com.example.teacherszone;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class stay extends Application {
    @Override
    public void onCreate() {
        super.onCreate()  ;

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){

            Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(stay.this,Home.class);



            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }
    }
}
