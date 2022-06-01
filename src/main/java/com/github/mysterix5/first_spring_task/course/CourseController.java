package com.github.mysterix5.first_spring_task.course;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PutMapping
    public void addParticipantToCourse(@RequestParam String studentId, @RequestParam String courseId){
        courseService.addStudentToCourse(studentId, courseId);
    }
}
