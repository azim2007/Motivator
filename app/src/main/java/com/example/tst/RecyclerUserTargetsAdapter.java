package com.example.tst;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerUserTargetsAdapter extends RecyclerView.Adapter<RecyclerUserTargetsAdapter.MyViewHolder> {
    private ArrayList<String> items;
    public RecyclerUserTargetsAdapter(ArrayList<String> items){
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Button targetButton;

        public MyViewHolder(final View v){
            super(v);
            targetButton = v.findViewById(R.id.stepButton);
        }
    }

    @NonNull
    @Override
    public RecyclerUserTargetsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_targets, parent, false);
        return new RecyclerUserTargetsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerUserTargetsAdapter.MyViewHolder holder, int position) {
        String nameTarget = items.get(position);
        holder.targetButton.setText(nameTarget);
        holder.targetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Button b = (Button) v;
                Log.i("Azim", b.getText().toString());
                MyTargetsActivity.selectedTarget = b.getText().toString();
                MyTargetsActivity.ChangeSelectedText();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
