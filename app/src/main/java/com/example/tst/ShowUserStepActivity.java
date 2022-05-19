package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ShowUserStepActivity extends AppCompatActivity {
    public static boolean isCurrent; //что бы отличать на какой шаг нажал юзер (текущий) или нет
    public static TargetLocal userTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_step);

        Log.i("Azim", "" + isCurrent);
    }
}