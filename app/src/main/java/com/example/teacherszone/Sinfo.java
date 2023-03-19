package com.example.teacherszone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Sinfo extends AppCompatActivity {

    EditText pclass,pdiv;
    Button search;
    RecyclerView recyclerView;
    sinfoadapter sinfoAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinfo);
        pclass = findViewById(R.id.pclass);
        pdiv = findViewById(R.id.pdiv);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search = findViewById(R.id.pseach);
        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(Sinfo.this, "sucess", Toast.LENGTH_SHORT).show();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();
                String Class = pclass.getText().toString();
                String Div = pdiv.getText().toString();
                FirebaseRecyclerOptions<sinfomodel> options =
                        new FirebaseRecyclerOptions.Builder<sinfomodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference("teachers").child(key).child("students").child("class").child(Class).child("div").child(Div), sinfomodel.class)
                                .build();

                sinfoAdapter = new sinfoadapter(options);
                sinfoAdapter.startListening();
                recyclerView.setAdapter(sinfoAdapter);



            }
        });

    }
}