package com.github.mysterix5.first_spring_task.student;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Student> getStudentsById(@PathVariable String id){
        return ResponseEntity.of(studentService.getStudentById(id));
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PutMapping
    public void changeStudentName(@RequestParam Optional<String> id, @RequestParam Optional<String> name){
        studentService.setStudentName(id, name);
    }

    @DeleteMapping
    public void deleteAllStudentsByPartName(@RequestParam Optional<String> partname){
        studentService.deleteAllStudentsByPartName(partname);
    }
}
