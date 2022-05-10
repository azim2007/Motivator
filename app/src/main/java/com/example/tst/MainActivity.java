package com.example.tst;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText numberOfTarget;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOfTarget = findViewById(R.id.edId);

    }



    public void GetTargetById(View view){
        String sNumberOfTarget = numberOfTarget.getText().toString();
        //method.setText("Get Method");
        new SigninActivity(this,status,role,0).execute(sNumberOfTarget);

    }

    /*public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Method");
        new SigninActivity(this,status,role,1).execute(username,password);
    }*/
}