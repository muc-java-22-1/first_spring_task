package com.github.mysterix5.first_spring_task.student;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping(path = "/search/{partname}")
    public List<Student> getStudentsByNameSearch(@PathVariable String partname){
        return studentService.getStudentsByNameSearch(partname);
    }

    @GetMapping(path = "/{id}")
    public Optional<Student> getStudentsById(@PathVariable String id){
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void setStudent(@RequestBody Student student){
        System.out.println(student);
        studentService.addStudent(student);
    }

    @PutMapping
    public void changeStudentName(@RequestParam Optional<Integer> index, @RequestParam Optional<String> name){

        studentService.setStudentName(index, name);
    }

    @DeleteMapping
    public void deleteAllStudentsByPartName(@RequestParam Optional<String> partname){

        studentService.deleteAllStudentsByPartName(partname);
    }
}
