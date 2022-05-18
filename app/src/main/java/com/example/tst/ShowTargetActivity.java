package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowTargetActivity extends AppCompatActivity {
    UserAndString tarNameAndUser;
    TextView header;
    Pusher pusher;
    RecyclerView recyclerView;
    int numberOfBranch; // нужен в showtarget изменяется при нажатии на стрелочки в активити
    public static String SelectedStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_target);

        Bundle arguments = getIntent().getExtras();
        tarNameAndUser = (UserAndString) arguments.getSerializable(UserAndString.class.getSimpleName());

        Log.i("Azim", tarNameAndUser.getString() + " " + tarNameAndUser.getUser().getName());
        header = findViewById(R.id.targetName);
        header.setText(tarNameAndUser.getString());

        pusher = new Pusher();
        pusher.UpdateTargetByName(tarNameAndUser.getString());

        recyclerView = findViewById(R.id.stepsView);
        RecyclerStepAdapter adapter = new RecyclerStepAdapter(new ArrayList<String>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        numberOfBranch = 0;
    }

    public void ShowTarget(View v){
        List<Step> stepsInBranch = new ArrayList<>();
        stepsInBranch = pusher.getBuf().getSteps().get(numberOfBranch);
        List<String> namesOfSteps = new ArrayList<>();
        for (Step e : stepsInBranch) {
            namesOfSteps.add(e.getName());
            Log.i("Azim", "" + e.getName());
        }
        Log.i("Azim", "" + pusher.getBuf().getName());
        RecyclerStepAdapter adapter = new RecyclerStepAdapter((ArrayList<String>) namesOfSteps);
        recyclerView.setAdapter(adapter);
    }
}