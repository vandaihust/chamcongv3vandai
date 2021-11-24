package com.vandai.mobi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.mobi.dto.EmployeeDto;
import com.vandai.mobi.model.Employee;
import com.vandai.mobi.services.DepartmentService;
import com.vandai.mobi.services.EmployeeService;
import com.vandai.mobi.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserService userService; 
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllEmployees(){
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees()
				,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.getEmployeeById(id);		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	@GetMapping("/department/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getEmployeeByDeparment(@PathVariable Long id){
		List<Employee> employees = employeeService.getEmployeeByDepartment(id);		
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	@GetMapping("search/{name}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchEmployeeByName(@PathVariable String name){
		List<Employee> listEmployees = employeeService.getEmployeeByName(name);
		return new ResponseEntity<List<Employee>>(listEmployees, HttpStatus.OK);
	}
	@GetMapping("{pageNo}/{pageSize}/{sortField}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> findPaginated(@PathVariable int pageNo,@PathVariable int pageSize,
			@PathVariable String sortField){
		Page<Employee> listEmployees = employeeService.findPaginated(pageNo, pageSize, sortField);
		return new ResponseEntity<Page<Employee>>(listEmployees, HttpStatus.OK);
	}
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto){		
		return new ResponseEntity<Employee>(employeeService.addEmployee(employeeDto)
				,HttpStatus.OK);
	}
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
		return new ResponseEntity<>(employeeService.updateEmployee(employee, id)
				, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
		return new ResponseEntity<>(employeeService.deleteEmployee(id)
				, HttpStatus.OK);
	}
}
