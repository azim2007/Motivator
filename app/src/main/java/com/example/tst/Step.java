package com.example.tst;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
// класс, описывающий этап на пути к цели
public class Step {
    private String description; // описание того, что дожен сделать юзер для прохождения этапа

    public String getName() {
        return name;
    }

    private String name; // имя шага
    private List<String> references = new ArrayList<String>(); // ссылки, например на видео войтенко (с огромными....)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public Step(){}
    public Step(String name, String description, @NonNull String... references){
        this.description = description;
        this.name = name;
        for(String s : references){
            this.references.add(s);
        }
    }
}
