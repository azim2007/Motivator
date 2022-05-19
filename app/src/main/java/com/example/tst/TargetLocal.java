package com.example.tst;

public class TargetLocal {//этот класс нужен для хранения на локальном устройстве
    //тк на локалке нужны переменные, отвечающие за то, какую ветку достижения цели выбрал юзер и на какой этапе он остановился
    private int selectedBranch;
    private int currentStep;

    public int getSelectedBranch() {
        return selectedBranch;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public Target getTarget() {
        return target;
    }

    private Target target;
    public TargetLocal (){}
    public TargetLocal (Target target, int branch){
        this.target = target;
        this.selectedBranch = branch;
        this.currentStep = 0;
    }
}
