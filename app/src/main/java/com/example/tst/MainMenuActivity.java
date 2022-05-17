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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] privets = {"Здравствуйте, ", "Добрый день, ", "Всего хорошего, "};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Bundle arguments = getIntent().getExtras();
        headerText = findViewById(R.id.headerText1);
        user = (User) arguments.getSerializable(User.class.getSimpleName());
        Log.i("Azim", user.getLogin() + " " + user.getName());
        Random r = new Random();
        headerText.setText(privets[r.nextInt() % 3] + user.getName()); //ну типа разные приветики)
    }

    public void SearchTargetActivity(View v){
        Intent intent = new Intent(this, SearchTargetActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        startActivity(intent);
    }
}