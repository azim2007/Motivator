package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    Pusher pusher;
    TextView message;
    public static int countOfUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pusher = new Pusher();
        message = findViewById(R.id.messageText);
        pusher.UpdateNameOfUsers(countOfUsers);
    }

    public void SignUp(View v){
        TextView name = findViewById(R.id.editTextName);
        TextView login = findViewById(R.id.editTextLogin2);
        TextView password = findViewById(R.id.editTextPassword2);
        Log.i("Azim", "userNames " + pusher.getNameOfUsers().get(0));
        if(pusher.getNameOfUsers().contains(login.getText().toString())){
            message.setText("юзер с таким логином уже существует, попробуйте другой логин");
        }

        else{
            User user = new User(name.getText().toString(), login.getText().toString(), password.getText().toString());
            pusher.PushUser(user);
            pusher = new Pusher();
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra(User.class.getSimpleName(), user);
            startActivity(intent);
        }
    }
}