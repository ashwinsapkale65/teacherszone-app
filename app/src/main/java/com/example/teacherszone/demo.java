package com.example.teacherszone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class demo extends AppCompatActivity implements Quantitylistner {
    EditText Dclass,Ddiv;
    Button Dsearch,Dsave;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    TextView Ddate;

    demoadapter Demoadapter;


    ArrayList<attendencemodel> mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        Ddate = findViewById(R.id.Ddate);
        Dsearch = findViewById(R.id.Dseach);
        Dsave = findViewById(R.id.save);

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(demo.this));


        Dsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                Ddate.setText(strDate);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();
                Dclass = findViewById(R.id.Dclass);
                Ddiv = findViewById(R.id.Ddiv);

                String DClass = Dclass.getText().toString();
                String DDiv = Ddiv.getText().toString();



                databaseReference = FirebaseDatabase.getInstance().getReference("teachers").child(key).child("students").child("class").child(DClass).child("div").child(DDiv);



                mlist = new ArrayList<>();

                Demoadapter = new demoadapter(demo.this,mlist,demo.this);

                recyclerView.setAdapter(Demoadapter);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            attendencemodel user1 = dataSnapshot.getValue(attendencemodel.class);
                            mlist.add(user1);

                        }
                        Demoadapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });



            }
        });
    }

    @Override
    public void onQuantityvhange(ArrayList<attendencemodel> mlist) {

        Dsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();

                String DClass = Dclass.getText().toString();
                String DDiv = Ddiv.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(DClass).child("div").child(DDiv).child("date").child(strDate).child("present").setValue(mlist);

            }
        });




    }
}