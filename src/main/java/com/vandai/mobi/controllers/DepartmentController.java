package com.vandai.mobi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vandai.mobi.model.Department;
import com.vandai.mobi.services.DepartmentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllDepartment(){
		departmentService.fakeDataDepartment();
		List<Department> listDepartment = departmentService.getAllDepartment();
		return new ResponseEntity<List<Department>>(listDepartment, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDeparmentById(@PathVariable Long id){
		Optional<Department> department = departmentService.getDepartmentById(id);		
		return new ResponseEntity<Optional<Department>>(department, HttpStatus.OK);
	}
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addDepartment(@RequestBody Department department){
		return new ResponseEntity<Department>(departmentService.addDepartment(department)
				, HttpStatus.OK);
	}
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateDepartment(@RequestBody Department department,@PathVariable Long id){
		return new ResponseEntity<>(departmentService.updateDepartment(department, id)
				, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
		return new ResponseEntity<>(departmentService.deleteDepartment(id)
				, HttpStatus.OK);
	}
	@DeleteMapping("all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAllDepartment(){
		return new ResponseEntity<>(departmentService.deleteAllDepartment()
				, HttpStatus.OK);
	}
//	@DeleteMapping("{ids}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> deleteDepartmentSelected(@PathVariable List<String> ids){
//		departmentService.deleteDepartmentSelected(ids);
//		return ResponseEntity.ok().body("Delete selected department success");
//	}
//	
	@GetMapping("search/{name}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchDepartment(@PathVariable String name){
		List<Department> listDepartment = departmentService.getDepartmentByName(name);
		return new ResponseEntity<List<Department>>(listDepartment, HttpStatus.OK);
	}
}
