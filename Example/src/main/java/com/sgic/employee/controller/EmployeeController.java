package com.sgic.employee.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.employee.entities.Employee;
import com.sgic.employee.service.EmployeeService;
import com.sgic.employeeDTO.EmployeeDto;
import com.sgic.helper.EmailHelper;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	static final String message = "Hello this is spring boot";

	@PostMapping("/employee")
	public ResponseEntity<Object> createIncomingSample(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return ResponseEntity.ok("Saved Successfully!");
	}

	@RequestMapping("/hello")
	public Map<String, String> callAsyncMethod() {
		Map map = new HashMap<Integer, String>();
		map.put("message", message);

		return map; // returns empty braces
	}

	@GetMapping(path = "/userDetails")
	public List<EmployeeDto> employeeDetails() {
		return employeeService.employeeDetails();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateDetails(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employee) {

		return ResponseEntity.ok().body(employeeService.updateEmployee(employeeId, employee));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteDetail(@PathVariable(value = "id") Long employeeId) {
		employeeService.deleteDetails(employeeId);
		return ResponseEntity.ok("Successfully Deleted");
	}

	@Autowired
	public JavaMailSender javaMailSender;

	@GetMapping("/email")
	public String sendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		// message.setFrom(username);
		message.setTo("suparoopan@gmail.com");
		message.setSubject("Spring Boot Application");
		message.setText("Hi this is simple mail from Spring Boot");
		javaMailSender.send(message);
		return ("Mail succesfully sent");
	}

}
