package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowTargetActivity extends AppCompatActivity {
    UserAndString tarNameAndUser;
    TextView header;
    Pusher pusher;
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
    }

    public void ShowTarget(View v){
        Log.i("Azim", "" + pusher.getBuf().getName());
    }
}