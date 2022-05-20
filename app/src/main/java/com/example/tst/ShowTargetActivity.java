package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowTargetActivity extends AppCompatActivity {
    UserAndString tarNameAndUser;
    TextView header, tNumberOfBranch, tMessage;
    Pusher pusher;
    RecyclerView recyclerView;
    int numberOfBranch; // нужен в showtarget изменяется при нажатии на стрелочки в активити
    public static String SelectedStep;
    public static int countOfTargets;
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
        pusher.UpdateTargetByName(tarNameAndUser.getString(), countOfTargets);
        Log.i("Azim", "countOfTargets2 " + countOfTargets);

        recyclerView = findViewById(R.id.stepsView);
        RecyclerStepAdapter adapter = new RecyclerStepAdapter(new ArrayList<String>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        numberOfBranch = 0;

        tNumberOfBranch = findViewById(R.id.branchNumber);
        tNumberOfBranch.setText("Ветка 1");

        tMessage = findViewById(R.id.textMessage);
        tMessage.setText("выберите ветку");
    }

    public void ShowTarget(View v){
        List<Step> stepsInBranch = new ArrayList<>();
        int countBranches = pusher.getBuf().getSteps().size();
        Log.i("Azim", "count branches" + countBranches);
        stepsInBranch = pusher.getBuf().getSteps().get(numberOfBranch % countBranches);
        tNumberOfBranch.setText("Ветка " + (numberOfBranch % countBranches + 1));
        List<String> namesOfSteps = new ArrayList<>();
        for (Step e : stepsInBranch) {
            namesOfSteps.add(e.getName());
            Log.i("Azim", "" + e.getName());
        }
        Log.i("Azim", "" + pusher.getBuf().getName());
        RecyclerStepAdapter adapter = new RecyclerStepAdapter((ArrayList<String>) namesOfSteps);
        recyclerView.setAdapter(adapter);

        //это для пуша
        pusher.UpdateUserTargets(tarNameAndUser.getUser(), countOfTargets);
    }

    public void ChangeBranch(View v){
        numberOfBranch++;
        ShowTarget();
    }

    public void ShowTarget(){
        List<Step> stepsInBranch = new ArrayList<>();
        int countBranches = pusher.getBuf().getSteps().size();
        Log.i("Azim", "count branches" + countBranches);
        stepsInBranch = pusher.getBuf().getSteps().get(numberOfBranch % countBranches);
        tNumberOfBranch.setText("Ветка " + (numberOfBranch % countBranches + 1));
        List<String> namesOfSteps = new ArrayList<>();
        for (Step e : stepsInBranch) {
            namesOfSteps.add(e.getName());
            Log.i("Azim", "" + e.getName());
        }
        Log.i("Azim", "" + pusher.getBuf().getName());
        RecyclerStepAdapter adapter = new RecyclerStepAdapter((ArrayList<String>) namesOfSteps);
        recyclerView.setAdapter(adapter);

        //это для пуша
        pusher.UpdateUserTargets(tarNameAndUser.getUser(), countOfTargets);
    }

    public void AddBranchIntoUserTargets(View v){
        int countBranches = pusher.getBuf().getSteps().size();
        //int countOfUserTargets = pusher.getUserTargets().size();
        boolean isTarget = true;
        for(TargetLocal e : pusher.getUserTargets()){
            Log.i("Azim", e.getTarget().getName() + " " + pusher.getBuf().getName());
            if(e.getTarget().getName().equals(pusher.getBuf().getName())){
                Log.i("Azim", "founded");
                isTarget = false;
                break;
            }
        }
        if(isTarget){
            //Log.i("Azim", "" + countOfUserTargets);
            TargetLocal userTarget = new TargetLocal(pusher.getBuf(), numberOfBranch % countBranches);
            pusher.PushUserTarget(tarNameAndUser.getUser(), userTarget);
            tMessage.setText("цель " + pusher.getBuf().getName() + " по ветке " + (numberOfBranch % countBranches + 1) + " успешно добавлена");
            Button b = (Button) v;
            b.setEnabled(false);
        }
        else {
            tMessage.setText("вы не можете добавить цель, которая у вас уже есть");
        }
    }
}