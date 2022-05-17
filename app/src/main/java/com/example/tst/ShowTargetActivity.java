package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ShowTargetActivity extends AppCompatActivity {
    UserAndString tarNameAndUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_target);
        Bundle arguments = getIntent().getExtras();
        tarNameAndUser = (UserAndString) arguments.getSerializable(UserAndString.class.getSimpleName());
        Log.i("Azim", tarNameAndUser.getString() + " " + tarNameAndUser.getUser().getName());
    }
}