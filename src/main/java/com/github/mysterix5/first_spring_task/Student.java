package com.github.mysterix5.first_spring_task;

import java.util.UUID;

public class Student {
    private String name;
    private String id;

    public Student(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getId() {
//        return id;
//    }
}
