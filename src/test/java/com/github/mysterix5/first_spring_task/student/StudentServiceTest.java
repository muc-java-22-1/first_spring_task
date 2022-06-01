package com.github.mysterix5.first_spring_task.student;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


class StudentServiceTest {

    @Test
    void addStudent() {
        // given
        StudentRepository mockedStudentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentService(mockedStudentRepository);
        Student student = new Student("Lukas");

        // when
        studentService.addStudent(student);

        // then
        verify(mockedStudentRepository).save(student);
    }

    @Test
    void getStudents() {
        // given
        StudentRepository mockedStudentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student("Lukas");
        Student student2 = new Student("Nicolai");
        when(mockedStudentRepository.findAll()).thenReturn(List.of(student, student2));
        StudentService studentService = new StudentService(mockedStudentRepository);

        // when
        var actual = studentService.getStudents();

        // then
        assertThat(actual).isEqualTo(List.of(student, student2));
    }

    @Test
    void setStudentName() {
    }

    @Test
    void getStudentsByNameSearch() {
        // given
        StudentRepository mockedStudentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student("Lukas");
        Student student2 = new Student("Nicolai");
        var studentList = List.of(student, student2);
        when(mockedStudentRepository.findAll())
                .thenReturn(studentList);
        StudentService studentService = new StudentService(mockedStudentRepository);

        // when
        var actual = studentService.getStudentsByNameSearch("uka");

        // then
        assertThat(actual).isEqualTo(List.of(student));
    }

    @Test
    void deleteAllStudentsByPartName() {
        // given
        StudentRepository mockedStudentRepository = Mockito.mock(StudentRepository.class);
        Student lukas = new Student("Lukas");
        Student bernd = new Student("Bernd");
        Student nicolai = new Student("Nicolai");
        var studentList = new ArrayList<>(List.of(lukas, bernd, nicolai));
        when(mockedStudentRepository.findAll()).thenReturn(studentList);
        StudentService studentService = new StudentService(mockedStudentRepository);

        // when
        studentService.deleteAllStudentsByPartName(Optional.of("l"));

        // then
        verify(mockedStudentRepository).findAll();
        verify(mockedStudentRepository).delete(lukas.getId());
        verify(mockedStudentRepository).delete(nicolai.getId());
        verify(mockedStudentRepository, never()).delete(bernd.getId());
    }

    @Test
    void getStudentById() {
        // given
        StudentRepository mockedStudentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student("Lukas");
        when(mockedStudentRepository.findById(student.getId()))
                .thenReturn(Optional.of(student));
        StudentService studentService = new StudentService(mockedStudentRepository);

        // when
        var actual = studentService.getStudentById(student.getId());

        if(actual.isEmpty()){
            fail();
        }

        // then
        assertThat(actual.get()).isEqualTo(student);
    }
}