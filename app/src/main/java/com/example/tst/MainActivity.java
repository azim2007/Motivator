package com.example.tst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText id;
    //private FirebaseListAdapter<User> adapter;
    private DatabaseReference dataBase;
    private Target target1;
    private Pusher pusher;
    private Target tar;
    private List<String> tars = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.edId);
        //age = findViewById(R.id.et_age);
        dataBase = FirebaseDatabase.getInstance().getReference();
        Step step1 = new Step("что то на русском", "ref1", "ref2", "ref3");
        Step step2 = new Step("еще что то на русском", "ref", "refdsf", "refgf");
        Step step3 = new Step("тратата", "itisnotref", "gh", "f");
        Step step4 = new Step("ееее", "ref1", "ref2", "ref3");
        Step step5 = new Step("о дааа, как я устал", "ref5", "refd", "refg");
        List<List<Step>> steps = CreateListList(CreateList(step3, step2, step1), CreateList(step4, step5));
        target1 = new Target("тест русиша", steps);
        pusher = new Pusher();
    }

    @NonNull
    private List<Step> CreateList(Step... steps){
        List<Step> st = new ArrayList<Step>();
        for (Step s : steps) {
            st.add(s);
        }
        return st;
    }

    @NonNull
    private  List<List<Step>> CreateListList(List<Step>... lists){
        List<List<Step>> st = new ArrayList<>();
        for(List<Step> l : lists){
            st.add(l);
        }
        return st;
    }

    public void onclickAdd(View v){
        pusher.PushTarget(target1);
        System.out.println("ok");
    }
    //пример работы с пушером, дада, это делается в телах двух отдельных функций (onClickGet, onClickToConsole), иначе всякая магия происходит...
    public void onClickGet(View v) {
        pusher.UpdateTargetByName(id.getText().toString()); //здесь мы обновляем значения в буфере
        pusher.UpdateNameOfTargets();
        //System.out.println("tar = " + tar.getName() + " " + tar.getSteps());
        System.out.println("buf = " + pusher.getBuf().getName() + " " + pusher.getBuf().getSteps());
        System.out.println("tars = " + tars);
        System.out.println("names = " + pusher.getTargetsNames());
    }

    public void onClickToConsole(View v){
        tars = pusher.getTargetsNames(); // а здесь мы получаем значения из буфера
        tar = pusher.getBuf();
        System.out.println("tars = " + tars);
        System.out.println("tar = " + tar.getName() + " " + tar.getSteps());
        System.out.println("names = " + pusher.getTargetsNames());
    }
}