package com.example.tst;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerStringAdapter extends RecyclerView.Adapter<RecyclerStringAdapter.MyViewHolder> {
    private ArrayList<String> items;
    public RecyclerStringAdapter(ArrayList<String> items){
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
    public RecyclerStringAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStringAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
