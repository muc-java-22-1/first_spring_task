package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    public void putStudent(String id, Student student){
        students.put(id, student);
    }

    public Optional<Student> getStudent(String id){
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> getAllStudents() {
        return students.values().stream().toList();
    }

    List<Student> getMutableStudentsList(){
        return new ArrayList<>(students.values());
    }

    public void deleteStudent(String id){
        students.remove(id);
    }
}
