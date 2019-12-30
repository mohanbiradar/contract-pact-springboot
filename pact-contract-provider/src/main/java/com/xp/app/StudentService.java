package com.xp.app;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	public Student saveStudent(Student student){
		/* for testing the provider end. This service will be mocked */
		return new Student("Ideal Name", "Pune", 100);
	}

}
