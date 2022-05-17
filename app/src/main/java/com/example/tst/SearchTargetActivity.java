package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchTargetActivity extends AppCompatActivity {
    List<String> items;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_target);
        items = new ArrayList<>();
        listfill();
        recyclerView = findViewById(R.id.targetsView);
        RecyclerStringAdapter adapter = new RecyclerStringAdapter((ArrayList<String>) items);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void listfill(){
        items.add("как поступить в ит вуз");
        items.add("как готовить яичницу");
        items.add("как сделать 3д модель");
        items.add("как лизнуть локоть");
    }

    public void RefreshRecyclerView(View v){
        listfill();
        RecyclerStringAdapter adapter = new RecyclerStringAdapter((ArrayList<String>) items);
        recyclerView.setAdapter(adapter);
    }
}