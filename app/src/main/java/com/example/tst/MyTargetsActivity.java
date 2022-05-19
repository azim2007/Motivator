package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyTargetsActivity extends AppCompatActivity {
    RecyclerView myTargets;
    TextView countOfUserTargets;
    public static TextView tSelectedTarget;
    User user;
    Pusher pusher;
    public static int countOfTargets;
    public static String selectedTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_targets);

        myTargets = findViewById(R.id.myTargets);

        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());

        RecyclerUserTargetsAdapter adapter = new RecyclerUserTargetsAdapter(new ArrayList<String>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        myTargets.setLayoutManager(manager);
        myTargets.setItemAnimator(new DefaultItemAnimator());
        myTargets.setAdapter(adapter);

        pusher = new Pusher();
        pusher.UpdateUserTargets(user, countOfTargets);
        Log.i("Azim", "" + countOfTargets);

        countOfUserTargets = findViewById(R.id.textCountUserTargets);

        tSelectedTarget = findViewById(R.id.textSelectedTarget);
    }

    public void ShowUserTargets(View v){
        List<String> namesOfUsertargets = new ArrayList<>();
        for(TargetLocal e : pusher.getUserTargets()){
            namesOfUsertargets.add(e.getTarget().getName());
        }

        RecyclerUserTargetsAdapter adapter = new RecyclerUserTargetsAdapter((ArrayList<String>) namesOfUsertargets);
        myTargets.setAdapter(adapter);
        countOfUserTargets.setText("всего " + namesOfUsertargets.size() + " небольших целей для большого счастья");
    }

    public static void ChangeSelectedText(){
        tSelectedTarget.setText("выбрано: " + selectedTarget);
    }

    public void ShowMyTargetActivity(View v){
        Intent intent = new Intent(this, ShowMyTargetActivity.class);
        UserAndString userAndNameTarget = new UserAndString(user, selectedTarget);
        intent.putExtra(UserAndString.class.getSimpleName(), userAndNameTarget);
        ShowMyTargetActivity.countOfTargets = pusher.getCountOfTargets();
        startActivity(intent);
    }
}