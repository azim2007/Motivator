package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchTargetActivity extends AppCompatActivity {
    List<String> items;
    RecyclerView recyclerView;
    Pusher pusher;
    TextView substringOfName;
    User thisUser;
    public static String buttonText;
    public static TextView selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_target);
        items = new ArrayList<>();
        pusher = new Pusher();

        substringOfName = findViewById(R.id.editTextSearchTarget);
        pusher.UpdateNameOfTargets("");

        recyclerView = findViewById(R.id.targetsView);
        RecyclerStringAdapter adapter = new RecyclerStringAdapter((ArrayList<String>) pusher.getTargetsNames());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        selected = findViewById(R.id.selectedTarget);

        Bundle arguments = getIntent().getExtras();
        thisUser = (User) arguments.getSerializable(User.class.getSimpleName());
    }

    public void FindTargetsWithSubstring(View v){
        pusher.UpdateNameOfTargets(substringOfName.getText().toString());
    }

    public void RefreshRecyclerView(View v){
        RecyclerStringAdapter adapter = new RecyclerStringAdapter((ArrayList<String>) pusher.getTargetsNames());
        recyclerView.setAdapter(adapter);
    }

    public void SearchTargetWithNameOnButton(View v){
        Button button = (Button) v;
        Log.i("Azim", button.getText().toString());
    }

    public static void SelectedTextChange(){
        selected.setText("Выбрано: " + buttonText);
    }

    public void ShowTargetActivity(View v){
        UserAndString tarNameAndUser = new UserAndString(thisUser, buttonText);
        Intent intent = new Intent(this, ShowTargetActivity.class);
        intent.putExtra(UserAndString.class.getSimpleName(), tarNameAndUser);
        startActivity(intent);
    }
}