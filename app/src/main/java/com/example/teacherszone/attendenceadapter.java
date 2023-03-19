package com.example.teacherszone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class attendenceadapter extends RecyclerView.Adapter<attendenceadapter.Viewholder> {
    Context context;
    ArrayList<attendencemodel> list;

    public attendenceadapter(Context context, ArrayList<attendencemodel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendencedesign,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull attendenceadapter.Viewholder holder, int position) {

        attendencemodel user= list.get(position);
        holder.t1.setText(user.getName());
        holder.t2.setText(user.getRoll());
        holder.t3.setText("");
        holder.t4.setText("");






        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();

                attendencestatus ob1 = new attendencestatus(user.getName(),user.getRoll());

                Toast.makeText(context, user.getName()+" Present", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(user.getCLASS()).child("div").child(user.getDIV()).child("date").child(strDate).child("absent").child(user.getRoll()).removeValue();

                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(user.getCLASS()).child("div").child(user.getDIV()).child("date").child(strDate).child("present").child(user.getRoll()).setValue(ob1);
                holder.t4.setText("");
                holder.t3.setTextColor(Color.GREEN);
                holder.t3.setText("PR");

            }
        });

        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();

                attendencestatus ob1 = new attendencestatus(user.getName(),user.getRoll());

                Toast.makeText(context, user.getName()+" Absent", Toast.LENGTH_SHORT).show();


                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(user.getCLASS()).child("div").child(user.getDIV()).child("date").child(strDate).child("present").child(user.getRoll()).removeValue();
                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("attendence").child("class").child(user.getCLASS()).child("div").child(user.getDIV()).child("date").child(strDate).child("absent").child(user.getRoll()).setValue(ob1);
                holder.t3.setText("");
                holder.t4.setTextColor(Color.RED);
                holder.t4.setText("AB");

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
        Button status,absent;



        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            t1= itemView.findViewById(R.id.t1);
            t2= itemView.findViewById(R.id.t2);
            t3 =itemView.findViewById(R.id.t3);
            t4 =itemView.findViewById(R.id.t4);

            status = itemView.findViewById(R.id.P);
            absent = itemView.findViewById(R.id.A);



        }
    }
}
