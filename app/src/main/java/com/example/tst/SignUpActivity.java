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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pusher = new Pusher();
        message = findViewById(R.id.messageText);
    }

    public void SignUp(View v){
        TextView name = findViewById(R.id.editTextName);
        TextView login = findViewById(R.id.editTextLogin2);
        TextView password = findViewById(R.id.editTextPassword2);
        User user = new User(name.getText().toString(), login.getText().toString(), password.getText().toString());
        pusher.PushUser(user);
        pusher = new Pusher();
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        startActivity(intent);
    }
}