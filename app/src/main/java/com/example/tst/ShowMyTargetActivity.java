package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowMyTargetActivity extends AppCompatActivity {
    UserAndString userAndNameTarget;
    TextView headerText;
    RecyclerView steps;
    public static TextView tMessage;
    Pusher pusher;
    public static int countOfTargets;
    public static String selectedStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_target);

        Bundle arguments = getIntent().getExtras();
        userAndNameTarget = (UserAndString) arguments.getSerializable(UserAndString.class.getSimpleName());

        pusher = new Pusher();
        pusher.UpdateUserTarget(userAndNameTarget.getUser(), userAndNameTarget.getString(), countOfTargets);

        headerText = findViewById(R.id.textNameOfUserTarget);
        headerText.setText(userAndNameTarget.getString());

        tMessage = findViewById(R.id.textSelectedStep);

        steps = findViewById(R.id.recyclerView);
        RecyclerUserStepAdapter adapter = new RecyclerUserStepAdapter(new ArrayList<String>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        steps.setLayoutManager(manager);
        steps.setItemAnimator(new DefaultItemAnimator());
        steps.setAdapter(adapter);
    }

    public void ShowUserStep(View v){
        TargetLocal target = pusher.getUserTarget();
        List<String> nameSteps = new ArrayList<>();
        List<Step> stSteps = target.getTarget().getSteps().get(target.getSelectedBranch());
        for(int i = 0; i < stSteps.size(); i++){
            if(i < target.getCurrentStep()){
                nameSteps.add(stSteps.get(i).getName() + "(выполнено)");
            }

            else if(i == target.getCurrentStep()){
                nameSteps.add(stSteps.get(i).getName() + "(текущий)");
            }

            else {
                nameSteps.add(stSteps.get(i).getName());
            }
        }
        RecyclerUserStepAdapter adapter = new RecyclerUserStepAdapter((ArrayList<String>) nameSteps);
        steps.setAdapter(adapter);
    }

    public static void ChangeSelectedText(){
        tMessage.setText("выбрано: " + selectedStep);
    }

    public void ShowUserStepActivity(View v){
        if(selectedStep.contains("(выполнено)")){
            tMessage.setText("вы уже выполнили этот шаг");
        }
        else if(selectedStep.contains("(текущий)")){
            ShowUserStepActivity.isCurrent = true;
            ActLoad();
        }
        else {
            ShowUserStepActivity.isCurrent = false;
            ActLoad();
        }
    }

    private void ActLoad(){
        TargetLocal target = pusher.getUserTarget();
        ShowUserStepActivity.userTarget = target;
        UserAndString userAndStepName = new UserAndString(userAndNameTarget.getUser(), selectedStep);
        Intent intent = new Intent(this, ShowUserStepActivity.class);
        intent.putExtra(UserAndString.class.getSimpleName(), userAndStepName);
        ShowUserStepActivity.countOfTargets = pusher.getCountOfTargets();
        startActivity(intent);
    }
}