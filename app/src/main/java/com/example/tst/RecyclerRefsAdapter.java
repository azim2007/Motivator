package com.example.tst;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerRefsAdapter extends RecyclerView.Adapter<RecyclerRefsAdapter.MyViewHolder> {
    private ArrayList<String> items;
    public RecyclerRefsAdapter(ArrayList<String> items){
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView ref;

        public MyViewHolder(final View v){
            super(v);
            ref = v.findViewById(R.id.textReferense);
        }
    }

    @NonNull
    @Override
    public RecyclerRefsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.references, parent, false);
        return new RecyclerRefsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerRefsAdapter.MyViewHolder holder, int position) {
        String nameTarget = items.get(position);
        holder.ref.setText(nameTarget);
        holder.ref.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                TextView b = (TextView) v;
                Log.i("Azim", b.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(b.getText().toString()));
                ShowUserStepActivity.refIntent = intent;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
