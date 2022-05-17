package com.example.tst;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
// класс, описывающий цель
public class Target {
    private String name;// название цели
    private List<List<Step>> steps = new ArrayList<>(); // список шагов (список списков, так как нужно разделение на возможные ветки развития)
    public String getName() {
        return name;
    }

    public List<List<Step>> getSteps() {
        return steps;
    }

    public Target(){}
    public Target(String name, List<List<Step>> steps){
        this.name = name;
        this.steps = steps;
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
}
