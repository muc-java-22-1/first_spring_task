package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        Student ronja = new Student("Ronja");
        Student birk = new Student("Birk");
        studentRepository.putStudent(ronja.getId(), ronja);
        studentRepository.putStudent(birk.getId(), birk);
    }

    public void addStudent(Student student){

        studentRepository.putStudent(student.getId(), student);
    }
    public List<Student> getStudents(){

        return studentRepository.getAllStudents();
    }

    public void setStudentName(Optional<String> id, Optional<String> name){
        if(id.isEmpty() || name.isEmpty()){
            return;
        }
        var student = studentRepository.getStudent(id.get());
        if(student.isEmpty()){
            return;
        }
        student.get().setName(name.get());
        studentRepository.putStudent(id.get(), student.get());
    }
    public List<Student> getStudentsByNameSearch(String partname){
        var students = studentRepository.getAllStudents();

        return students.stream()
                .filter(s->s.getName().toLowerCase().contains(partname.toLowerCase()))
                .toList();
    }

    public void deleteAllStudentsByPartName(Optional<String> partname){
        if(partname.isEmpty()){
            return;
        }

        var studentsToDelete =  studentRepository.getAllStudents().stream()
                .filter(s->s.getName().toLowerCase().contains(partname.get().toLowerCase())).toList();

        for(var s : studentsToDelete){
            studentRepository.deleteStudent(s.getId());
        }
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.getStudent(id);
    }
}
