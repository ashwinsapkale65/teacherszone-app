package com.example.teacherszone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class sinfoadapter extends FirebaseRecyclerAdapter<sinfomodel,sinfoadapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public sinfoadapter(@NonNull @NotNull FirebaseRecyclerOptions<sinfomodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull sinfoadapter.myViewHolder holder, int position, @NonNull @NotNull sinfomodel model) {
        holder.t1.setText(model.getName());



        holder.t2.setText(model.getRoll());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String key = firebaseUser.getUid();

                FirebaseDatabase.getInstance().getReference("teachers").child(key).child("students").child("class").child(model.getCLASS()).child("div").child(model.getDIV()).child(model.getRoll()).removeValue();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sinfodesign,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        Button delete;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.t1);

            t2=(TextView)itemView.findViewById(R.id.t2);
            delete = itemView.findViewById(R.id.delete);



        }
    }


}
