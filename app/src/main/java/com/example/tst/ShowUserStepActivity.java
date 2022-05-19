package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowUserStepActivity extends AppCompatActivity {
    public static boolean isCurrent; //что бы отличать на какой шаг нажал юзер (текущий) или нет
    public static TargetLocal userTarget;
    public static Intent refIntent;
    public static int countOfTargets;
    UserAndString userAndNameStep;
    RecyclerView refs;
    TextView header, stepDescription;
    Pusher pusher;
    Button complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_step);

        Log.i("Azim", "" + isCurrent);

        Bundle arguments = getIntent().getExtras();
        userAndNameStep = (UserAndString) arguments.getSerializable(UserAndString.class.getSimpleName());

        complete = (Button) findViewById(R.id.button16);
        complete.setEnabled(isCurrent);

        refs = findViewById(R.id.referencesView);
        RecyclerRefsAdapter adapter = new RecyclerRefsAdapter(new ArrayList<String>());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        refs.setLayoutManager(manager);
        refs.setItemAnimator(new DefaultItemAnimator());
        refs.setAdapter(adapter);

        pusher = new Pusher();

        header = findViewById(R.id.textTargetName);
        stepDescription = findViewById(R.id.textStepDescription);
        header.setText(userAndNameStep.getString());
    }

    public void CompleteActivity(View v){
        Log.i("Azim", "complete");
        if(userTarget.getCurrentStep() + 1 != userTarget.getTarget().getSteps().get(userTarget.getSelectedBranch()).size()){
            pusher.PushUserCompleteStep(userAndNameStep.getUser(), userTarget, userTarget.getCurrentStep() + 1);
            CompleteActivity.isTargetComplete = false;
            pusher.PushStarsToUser(userAndNameStep.getUser(), userAndNameStep.getUser().getStarsCount() + 1);
            ActLoad();
        }

        else{
            pusher.DeleteUserTarget(userAndNameStep.getUser(), userTarget, countOfTargets);
            pusher.PushStarsToUser(userAndNameStep.getUser(), userAndNameStep.getUser().getStarsCount() + 5);
            CompleteActivity.isTargetComplete = true;
            ActLoad();
        }
    }

    private void ActLoad(){
        Intent intent = new Intent(this, CompleteActivity.class);
        intent.putExtra(User.class.getSimpleName(), userAndNameStep.getUser());
        startActivity(intent);
    }

    public void InternetSearch(View v){
        startActivity(refIntent);
    }

    public void ShowStep(View v){
        List<Step> branch = userTarget.getTarget().getSteps().get(userTarget.getSelectedBranch());
        Step selectedStep = new Step();
        for(Step e : branch){
            if(e.getName().equals(userAndNameStep.getString().replace("(текущий)", ""))){
                selectedStep = e;
                break;
            }
        }

        stepDescription.setText(selectedStep.getDescription());
        RecyclerRefsAdapter adapter = new RecyclerRefsAdapter((ArrayList<String>) selectedStep.getReferences());
        refs.setAdapter(adapter);
    }
}