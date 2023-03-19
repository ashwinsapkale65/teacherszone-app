package com.example.teacherszone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class demoadapter extends RecyclerView.Adapter<demoadapter.Viewholder> {
    Context context;
    ArrayList<attendencemodel>  mlist;

    Quantitylistner quantitylistner;

    ArrayList<attendencemodel> arrayList = new ArrayList<attendencemodel>();


    public demoadapter(Context context, ArrayList<attendencemodel> mlist, Quantitylistner quantitylistner) {
        this.context = context;
        this.mlist = mlist;
        this.quantitylistner = quantitylistner;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demodesign,parent,false);
        return new demoadapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull demoadapter.Viewholder holder, int position) {
        attendencemodel user1= mlist.get(position);
        holder.t1.setText(user1.getName());
        holder.t2.setText(user1.getRoll());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlist != null && mlist.size()>0){
                    holder.checkBox.setText(user1.getName());
                    if(holder.checkBox.isChecked()){
                        arrayList.add(mlist.get(position));



                    }
                    else {
                        arrayList.remove(mlist.get(position));






                    }
                    quantitylistner.onQuantityvhange(arrayList);
                }

            }
        });




    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        CheckBox checkBox;


        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            t1= itemView.findViewById(R.id.t1);
            t2= itemView.findViewById(R.id.t2);
            checkBox = itemView.findViewById(R.id.Check);
        }
    }
}
