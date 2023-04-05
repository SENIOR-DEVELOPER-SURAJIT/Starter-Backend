package com.main.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.rest.exception.ResourceNotFoundException;
import com.main.rest.model.Employee;
import com.main.rest.repository.EmployeeRepository;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	// update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmailid(employeeDetails.getEmailid());

		Employee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	//delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> responce = new HashMap<>();
		responce.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(responce);
	}
	//delete all rest api
	@DeleteMapping("/deleteAll")
	public void deleteEmployee() {
		employeeRepository.deleteAll();
	}
}
