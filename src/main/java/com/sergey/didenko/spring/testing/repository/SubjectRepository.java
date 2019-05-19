package com.sergey.didenko.spring.testing.repository;

import com.sergey.didenko.spring.testing.domain.Student;
import com.sergey.didenko.spring.testing.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByStudent(Student student);

}