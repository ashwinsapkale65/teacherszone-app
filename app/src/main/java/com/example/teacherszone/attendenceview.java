package com.example.teacherszone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class attendenceview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText vclass,vdiv,vdate;
    Button vview;
    Spinner spinner;
    RecyclerView recyclerView;

    attendenceviewadapter mainadaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendenceview);
        vclass = findViewById(R.id.vclass);
        vdiv = findViewById(R.id.vdiv);
        vdate = findViewById(R.id.vdate);
        vview = findViewById(R.id.vview);
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);








    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        vview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = parent.getSelectedItem().toString();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();
                String VClass = vclass.getText().toString();
                String VDiv = vdiv.getText().toString();
                String VDate = vdate.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");



                if(item.equals("Present")){
                    FirebaseRecyclerOptions<attendenceviewmodel> options =
                            new FirebaseRecyclerOptions.Builder<attendenceviewmodel>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(VClass).child("div").child(VDiv).child("date").child(VDate).child("present"), attendenceviewmodel.class)
                                    .build();
                    mainadaper = new attendenceviewadapter(options);
                    mainadaper.startListening();
                    recyclerView.setAdapter(mainadaper);

                }

                else
                {
                    FirebaseRecyclerOptions<attendenceviewmodel> options =
                            new FirebaseRecyclerOptions.Builder<attendenceviewmodel>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(VClass).child("div").child(VDiv).child("date").child(VDate).child("absent"), attendenceviewmodel.class)
                                    .build();
                    mainadaper = new attendenceviewadapter(options);
                    mainadaper.startListening();
                    recyclerView.setAdapter(mainadaper);
                }
                


            }
        });


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select First", Toast.LENGTH_SHORT).show();

    }


}