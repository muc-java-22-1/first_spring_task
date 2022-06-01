package com.github.mysterix5.first_spring_task.course;

import com.github.mysterix5.first_spring_task.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {

    private final String id = UUID.randomUUID().toString();
    private String name;
    private final List<Student> participants = new ArrayList<>();

    public Course(){}
    public Course(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<Student> getParticipants() {
        return participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addParticipant(Student student){
        participants.add(student);
    }
}
