package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student("Ronja"));
        students.add(new Student("Birk"));
    }

    public void addStudent(Student student){
        students.add(student);
    }
    public List<Student> getStudents(){
        return students;
    }

    public void setStudentName(Optional<Integer> index, Optional<String> name){
        if(index.isEmpty() || name.isEmpty()){
            return;
        }
        students.get(index.get()).setName(name.get());
    }
    public List<Student> getStudentsByNameSearch(String partname){
        return students.stream()
                .filter(s->s.getName().toLowerCase().contains(partname.toLowerCase()))
                .toList();
    }

    public void deleteAllStudentsByPartName(Optional<String> partname){
        if(partname.isEmpty()){
            return;
        }
        students =  students.stream()
                .filter(s->!s.getName().toLowerCase().contains(partname.get().toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Student> getStudentById(String id) {
        return students.stream().filter(s -> s.getId().equals(id))
                .findFirst();
    }
}
