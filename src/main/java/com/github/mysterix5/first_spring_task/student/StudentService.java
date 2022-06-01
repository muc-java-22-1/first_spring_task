package com.github.mysterix5.first_spring_task.student;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        Student ronja = new Student("Ronja");
        Student birk = new Student("Birk");
        studentRepository.save(ronja);
        studentRepository.save(birk);
    }

    public void addStudent(Student student){

        studentRepository.save(student);
    }
    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public void setStudentName(Optional<String> id, Optional<String> name){
        if(id.isEmpty() || name.isEmpty()){
            return;
        }
        var student = studentRepository.findById(id.get());
        if(student.isEmpty()){
            return;
        }
        student.get().setName(name.get());
        studentRepository.save(student.get());
    }
    public List<Student> getStudentsByNameSearch(String partname){
        var students = studentRepository.findAll();

        return students.stream()
                .filter(s->s.getName().toLowerCase().contains(partname.toLowerCase()))
                .toList();
    }

    public void deleteAllStudentsByPartName(Optional<String> partname){
        if(partname.isEmpty()){
            return;
        }

        var studentsToDelete =  studentRepository.findAll().stream()
                .filter(s->s.getName().toLowerCase().contains(partname.get().toLowerCase())).toList();

        for(var s : studentsToDelete){
            studentRepository.delete(s.getId());
        }
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }
}
