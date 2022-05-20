package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainMenuActivity extends AppCompatActivity {
    User user;
    TextView headerText;
    Pusher pusher;
    public static User statUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] privets = {"Здравствуйте, ", "Добрый день, ", "Всего хорошего, "};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        try{
            statUser.getName();
            user = statUser;
            statUser = null;
        }
        catch(Exception e){
            Bundle arguments = getIntent().getExtras();
            user = (User) arguments.getSerializable(User.class.getSimpleName());
        }

        headerText = findViewById(R.id.headerText1);

        Log.i("Azim", user.getLogin() + " " + user.getName() + " " + user.getStarsCount());
        Random r = new Random();
        headerText.setText(privets[r.nextInt(3)] + user.getName()); //ну типа разные приветики)

        pusher = new Pusher();
    }

    public void SearchTargetActivity(View v){
        Intent intent = new Intent(this, SearchTargetActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        startActivity(intent);
    }

    public void MyTargetsActivity(View v){
        Intent intent = new Intent(this, MyTargetsActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        MyTargetsActivity.countOfTargets = pusher.getCountOfTargets();
        startActivity(intent);
    }

    public void UserCabinetActivity(View v){
        Intent intent = new Intent(this, UserCabinetActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        UserCabinetActivity.userCount = pusher.getCountOfUsers();
        startActivity(intent);
    }

    public void MyStepsActivity(View v){
        Intent intent = new Intent(this, CurrentStepsActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        CurrentStepsActivity.countOfTargets = pusher.getCountOfTargets();
        startActivity(intent);
    }
}