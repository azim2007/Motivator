package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CompleteActivity extends AppCompatActivity {
    public static boolean isTargetComplete;
    public TextView tMessage;
    User user;
    Pusher pusher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        tMessage = findViewById(R.id.textMessageComplete);
        if(isTargetComplete){
            tMessage.setText("так держать, вы выполнили одну из своих целей, за это вы получаете 5 звездочек!");
        }

        else {
            tMessage.setText("вы еще сильнее приблизились к исполнению вашей цели, еще немного и она будет достигнута. за старания вы получаете 1 звездочку!");
        }

        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());

        pusher = new Pusher();
        pusher.UpdateThisUser(user.getLogin(), user.getPassword());
    }

    public void MenuActivity(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);
        MainMenuActivity.statUser = user;
        Log.i("Azim", user.getName());
        startActivity(intent);
    }
}