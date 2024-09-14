package com.bhavani.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhavani.exception.ResourceNotFoundException;
import com.bhavani.model.Employee;
import com.bhavani.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return empRepo.findAll();
	}
	
	//creating a employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody
			Employee employee)
	{
		return empRepo.save(employee);
	}
	//get employee by Id rest API
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id)
	{
		Employee emp= empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee)
	{
		Employee emp= empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id"+id));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		empRepo.save(emp);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
				
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable("id") Long id)
	{
		Employee emp= empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id"+id));
		empRepo.delete(emp);
		Map<String, Boolean> result= new HashMap<>();
		result.put("deleted",Boolean.TRUE);
		return new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
		
	}

}
