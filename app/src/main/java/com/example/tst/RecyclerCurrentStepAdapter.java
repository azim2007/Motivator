package com.example.tst;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCurrentStepAdapter extends RecyclerView.Adapter<RecyclerCurrentStepAdapter.MyViewHolder> {
    private ArrayList<ArrayList<String>> items;
    public RecyclerCurrentStepAdapter(ArrayList<ArrayList<String>> items){
        this.items = items;
        try {
            for(String e : items.get(0)){
                Log.i("Azim", "items " + e);
            }
        }
        catch (Exception e){
            Log.i("Azim", "items null");
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Button targetButton;
        private TextView targetName;

        public MyViewHolder(final View v){
            super(v);
            targetButton = v.findViewById(R.id.button23);
            targetName = v.findViewById(R.id.textNameTarget);
        }
    }

    @NonNull
    @Override
    public RecyclerCurrentStepAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_current_steps, parent, false);
        return new RecyclerCurrentStepAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCurrentStepAdapter.MyViewHolder holder, int position) {
        String nameTarget = items.get(position).get(0);
        String nameStep = items.get(position).get(1);
        try {
            Log.i("Azim", "onBind " + nameStep);

        }
        catch (Exception e){
            Log.i("Azim", "onBind null");
        }
        holder.targetButton.setText(nameTarget);
        holder.targetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Button b = (Button) v;
                Log.i("Azim", b.getText().toString());
                CurrentStepsActivity.nameOfStep = nameTarget;
                CurrentStepsActivity.nameOfTarget = nameStep;
                CurrentStepsActivity.ChangeSelectedText();
            }
        });
        holder.targetName.setText(nameStep);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
