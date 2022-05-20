package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserCabinetActivity extends AppCompatActivity {
    User user;
    Pusher pusher;
    TextView name, login, starCount;
    public static int userCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cabinet);

        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());

        pusher = new Pusher();
        pusher.UpdateThisUser(user.getLogin(), user.getPassword(), userCount);

        name = findViewById(R.id.textUserName);
        login = findViewById(R.id.textUserLogin);
        starCount = findViewById(R.id.textUserStarCount);

        name.setText("имя: " + user.getName());
        login.setText("логин: " + user.getLogin());
        starCount.setText("кол-во звезд: " + user.getStarsCount());
    }

    public void UpdateUser(View v){
        name.setText("имя: " + pusher.getThisUser().getName());
        login.setText("логин: " + pusher.getThisUser().getLogin());
        starCount.setText("кол-во звезд: " + pusher.getThisUser().getStarsCount());
    }
}