package com.example.tst;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStepAdapter extends RecyclerView.Adapter<RecyclerStepAdapter.MyViewHolder> {
    private ArrayList<String> items;
    public RecyclerStepAdapter(ArrayList<String> items){
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
    public RecyclerStepAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_targets, parent, false);
        return new RecyclerStepAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStepAdapter.MyViewHolder holder, int position) {
        String nameTarget = items.get(position);
        holder.targetButton.setText(nameTarget);
        holder.targetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Button b = (Button) v;
                Log.i("Azim", b.getText().toString());
                ShowTargetActivity.SelectedStep = b.getText().toString();
                SearchTargetActivity.SelectedTextChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
