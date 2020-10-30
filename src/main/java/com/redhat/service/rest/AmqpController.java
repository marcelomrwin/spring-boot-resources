package com.redhat.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.service.amqp.AMQPService;
import com.redhat.service.model.People;
import com.redhat.service.model.Student;
import com.redhat.service.repository.PeopleRepository;
import com.redhat.service.repository.StudentRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/amqp")
public class AmqpController {

	@Autowired
	protected AMQPService amqpService;
	
	@Autowired
	protected PeopleRepository peopleRepository;
	
	@Autowired
	protected StudentRepository studentRepository;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/people")
	public ResponseEntity<People> send(@RequestBody People people){
		People savedPeople = peopleRepository.save(people);
		amqpService.sendMessage(savedPeople);
		logger.info("People saved {}",people);        
        return ResponseEntity.ok(savedPeople);
	}
	
	@PostMapping("/student")
	public ResponseEntity<Student> send(@RequestBody Student student){
		Student savedStudent = studentRepository.save(student);
		amqpService.sendMessage(savedStudent);
		logger.info("Student saved {}",student);        
        return ResponseEntity.ok(savedStudent);
	}
}
