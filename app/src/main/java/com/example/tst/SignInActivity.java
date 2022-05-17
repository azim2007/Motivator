package com.example.tst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private Pusher pusher;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        pusher = new Pusher();
        message = findViewById(R.id.textMesage);
    }

    public void SignInOk(View v){
        TextView login = findViewById(R.id.editTextLogin);
        TextView password = findViewById(R.id.editTextPassword);
        pusher.UpdateThisUser(login.getText().toString(), password.getText().toString());
    }

    public void SignInNext(View v){
        User user = pusher.getThisUser();
        if(user == null){
            Log.i("Azim", "incorrect");
            message.setText("неправильный логин или пароль");
        }
        else{
            Log.i("Azim", "correct");
            message.setText("");
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra(User.class.getSimpleName(), user);
            startActivity(intent);
        }
        pusher = new Pusher();
    }
}