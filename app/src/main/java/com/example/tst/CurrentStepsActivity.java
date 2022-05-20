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

public class CurrentStepsActivity extends AppCompatActivity {
    public static String nameOfStep, nameOfTarget;
    public static int countOfTargets;
    Pusher pusher;
    User user;
    static TextView tSelected;
    RecyclerView currentSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_steps);
        tSelected = findViewById(R.id.textSelectedCurrentStep);

        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());

        pusher = new Pusher();
        pusher.UpdateUserTargets(user, countOfTargets);
        Log.i("Azim", "" + countOfTargets);

        currentSteps = findViewById(R.id.recyclerCurrentSteps);
        RecyclerCurrentStepAdapter adapter = new RecyclerCurrentStepAdapter(new ArrayList<ArrayList<String>>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        currentSteps.setLayoutManager(manager);
        currentSteps.setItemAnimator(new DefaultItemAnimator());
        currentSteps.setAdapter(adapter);
    }

    public static void ChangeSelectedText(){
        tSelected.setText("выбрано: " + nameOfStep);
    }

    public void ShowUserSteps(View v){
        List<TargetLocal> userTargets = pusher.getUserTargets();
        ArrayList<ArrayList<String>> userTargetAndStepsNames = new ArrayList<>();
        for(TargetLocal e : userTargets){
            ArrayList<String> names = new ArrayList<>();

            Log.i("Azim", "" + e.getTarget().getName());
            names.add(e.getTarget().getSteps().get(e.getSelectedBranch()).get(e.getCurrentStep()).getName());
            names.add(e.getTarget().getName());
            userTargetAndStepsNames.add(names);
        }
        RecyclerCurrentStepAdapter adapter = new RecyclerCurrentStepAdapter(userTargetAndStepsNames);
        currentSteps.setAdapter(adapter);
    }

    public void ShowUserStepActivity1(View v){
        ShowUserStepActivity.isCurrent = true;
        List<TargetLocal> userTargets = pusher.getUserTargets();
        TargetLocal target = new TargetLocal();
        for(TargetLocal e : userTargets){
            if(e.getTarget().getName() == nameOfTarget){
                target = e;
            }
        }

        ShowUserStepActivity.userTarget = target;
        UserAndString userAndStepName = new UserAndString(user, nameOfStep);
        Intent intent = new Intent(this, ShowUserStepActivity.class);
        intent.putExtra(UserAndString.class.getSimpleName(), userAndStepName);
        ShowUserStepActivity.countOfTargets = pusher.getCountOfTargets();
        startActivity(intent);
    }
}