package com.example.tst;

public class TargetLocal extends Target {//этот класс нужен для хранения на локальном устройстве
    //тк на локалке нужны переменные, отвечающие за то, какую ветку достижения цели выбрал юзер и на какой этапе он остановился
    private int selectedBranch;
    private int currentStep;
    public TargetLocal (){}
}
