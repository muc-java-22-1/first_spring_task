package com.github.mysterix5.first_spring_task.course;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final Map<String, Course> courses = new HashMap<>();

    public CourseRepository(){
        this.save(new Course("Java"));
        this.save(new Course("JavaScript"));
    }

    public Optional<Course> findById(String id){
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findAll(){
        return courses.values().stream().toList();
    }

    public void save(Course course){
        courses.put(course.getId(), course);
    }


}
