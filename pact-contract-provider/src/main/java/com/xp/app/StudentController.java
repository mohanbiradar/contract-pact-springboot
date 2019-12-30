package com.xp.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	@PostMapping(value="/student",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Student saveStudent(@RequestBody Student student){
		return studentService.saveStudent(student);
	}

}
