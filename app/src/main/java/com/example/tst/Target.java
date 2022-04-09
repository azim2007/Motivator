package com.example.tst;

import java.util.ArrayList;
import java.util.List;
// класс, описывающий цель
public class Target {
    private String name;// название цели
    private List<List<Step>> steps = new ArrayList<>(); // список шагов (список списков, так как нужно разделение на возможные ветки развития)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Step>> getSteps() {
        return steps;
    }

    public void setSteps(List<List<Step>> steps) {
        this.steps = steps;
    }

    public Target(){}
    public Target(String name, List<List<Step>> steps){
        this.name = name;
        this.steps = steps;
    }
}
