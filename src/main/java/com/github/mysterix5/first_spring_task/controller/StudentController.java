package com.github.mysterix5.first_spring_task.controller;

import com.github.mysterix5.first_spring_task.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController(){
        students.add(new Student("Ronja"));
        students.add(new Student("Birk"));
    }

    @GetMapping(path = "/{partname}")
    public List<Student> getStudentsByNameSearch(@PathVariable Optional<String> partname){
        if(partname.isEmpty()){
            return students;
        }
        return students.stream()
                .filter(s->s.getName().toLowerCase().contains(partname.get().toLowerCase()))
                .toList();
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return students;
    }

    @PostMapping
    public void setStudent(@RequestParam Optional<String> name){
        students.add(new Student(name.orElse("You idiot didnt give me a name")));
    }

    @PutMapping
    public void changeStudentName(@RequestParam Optional<Integer> index, @RequestParam Optional<String> name){
        if(index.isEmpty() || name.isEmpty()){
            return;
        }
        students.get(index.get()).setName(name.get());
    }

    @DeleteMapping
    public void deleteAllStudentsByPartName(@RequestParam Optional<String> partname){
        if(partname.isEmpty()){
            return;
        }
        students =  students.stream()
                .filter(s->!s.getName().toLowerCase().contains(partname.get().toLowerCase()))
                .collect(Collectors.toList());
    }
}
