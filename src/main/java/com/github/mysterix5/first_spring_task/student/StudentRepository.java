package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    public void save(Student student){
        students.put(student.getId(), student);
    }

    public Optional<Student> findById(String id){
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> findAll() {
        return students.values().stream().toList();
    }

    public void delete(String id){
        students.remove(id);
    }
}
