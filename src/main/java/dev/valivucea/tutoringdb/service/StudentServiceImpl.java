package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.valivucea.tutoringdb.model.Student;
import dev.valivucea.tutoringdb.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found - Id: " + id));

        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found - Id: " + id));
    }

    @Override
    public List<Student> getStudentListOrdered() {
        return (List<Student>) studentRepository.findAll(
            Sort.by(Sort.Direction.ASC, "firstName").and(Sort.by(Sort.Direction.ASC, "lastName"))
        );
    }
    
    @Override
    public Page<Student> getStudentListPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return (Page<Student>) studentRepository.findAll(pageable);
    }
}
