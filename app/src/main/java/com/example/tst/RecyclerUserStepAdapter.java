package com.example.tst;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerUserStepAdapter extends RecyclerView.Adapter<RecyclerUserStepAdapter.MyViewHolder> {
    private ArrayList<String> items;
    public RecyclerUserStepAdapter(ArrayList<String> items){
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Button targetButton;

        public MyViewHolder(final View v){
            super(v);
            targetButton = v.findViewById(R.id.targetButton);
        }
    }

    @NonNull
    @Override
    public RecyclerUserStepAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_targets, parent, false);
        return new RecyclerUserStepAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerUserStepAdapter.MyViewHolder holder, int position) {
        String nameTarget = items.get(position);
        holder.targetButton.setText(nameTarget);
        holder.targetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Button b = (Button) v;
                Log.i("Azim", b.getText().toString());
                ShowMyTargetActivity.selectedStep = b.getText().toString();
                ShowMyTargetActivity.ChangeSelectedText();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
