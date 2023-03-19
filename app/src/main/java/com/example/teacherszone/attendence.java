package com.example.teacherszone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class attendence extends AppCompatActivity{
    EditText aclass,adiv;
    Button asearch;
    RecyclerView recyclerView;
    attendenceadapter Attendenceadapter;
    DatabaseReference databaseReference;
    ArrayList<attendencemodel> list;

    TextView ldate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);



        aclass = findViewById(R.id.aclass);


        adiv = findViewById(R.id.adiv);
        recyclerView = findViewById(R.id.recycler);

        asearch = findViewById(R.id.aseach);
        
        











    }
    public void search(View view) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();

        ldate = findViewById(R.id.ldate);
        ldate.setText(strDate);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String key = firebaseUser.getUid();


        String Aclass = aclass.getText().toString();
        String Adiv = adiv.getText().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference("teachers").child(key).child("students").child("class").child(Aclass).child("div").child(Adiv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        Attendenceadapter = new attendenceadapter(this,list);
        recyclerView.setAdapter(Attendenceadapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    attendencemodel user = dataSnapshot.getValue(attendencemodel.class);
                    list.add(user);

                }
                Attendenceadapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        
        




    }


}
