package com.redhat.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.redhat.service.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}
