package com.github.mysterix5.first_spring_task.course;

import com.github.mysterix5.first_spring_task.student.Student;
import com.github.mysterix5.first_spring_task.student.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseService(CourseRepository courseRepository, StudentService studentService){
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addStudentToCourse(String studentId, String courseId){
        Optional<Student> student = studentService.getStudentById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if(student.isPresent() && course.isPresent()){
            course.get().addParticipant(student.get());
            courseRepository.save(course.get());
        }
    }
}
