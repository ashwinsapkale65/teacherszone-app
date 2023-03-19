package com.example.teacherszone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class attendenceviewadapter extends FirebaseRecyclerAdapter<attendenceviewmodel,attendenceviewadapter.Myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public attendenceviewadapter(@NonNull @NotNull FirebaseRecyclerOptions<attendenceviewmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull attendenceviewadapter.Myviewholder holder, int position, @NonNull @NotNull attendenceviewmodel model) {
        holder.vname.setText(model.getName());
        holder.vroll.setText(model.getRoll());
    }

    @NonNull
    @NotNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendenceviewdesign,parent,false);
        return new Myviewholder(view);
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView vname,vroll;

        public Myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            vname = itemView.findViewById(R.id.vname);
            vroll = itemView.findViewById(R.id.vroll);
        }
    }
}
