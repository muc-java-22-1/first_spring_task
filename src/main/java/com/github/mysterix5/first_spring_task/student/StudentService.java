package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<String, Student> students = new HashMap<>();

    public StudentService() {
        Student ronja = new Student("Ronja");
        Student birk = new Student("Birk");
        students.put(ronja.getId(), ronja);
        students.put(birk.getId(), birk);
    }

    public void addStudent(Student student){
        students.put(student.getId(), student);
    }
    public List<Student> getStudents(){
        return students.values().stream().toList();
    }

    public void setStudentName(Optional<String> id, Optional<String> name){
        if(id.isEmpty() || name.isEmpty()){
            return;
        }
        students.get(id.get()).setName(name.get());
    }
    public List<Student> getStudentsByNameSearch(String partname){
        return students.values().stream()
                .filter(s->s.getName().toLowerCase().contains(partname.toLowerCase()))
                .toList();
    }

    public void deleteAllStudentsByPartName(Optional<String> partname){
        if(partname.isEmpty()){
            return;
        }
        students =  students.values().stream()
                .filter(s->!s.getName().toLowerCase().contains(partname.get().toLowerCase()))
                .collect(Collectors.toMap(Student::getId, s->s));
    }

    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(students.get(id));
    }
}
