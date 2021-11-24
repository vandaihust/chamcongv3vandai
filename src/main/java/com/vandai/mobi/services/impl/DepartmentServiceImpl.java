package com.vandai.mobi.services.impl;

import java.util.List;
import java.util.Optional;

import com.vandai.mobi.model.Department;

public interface DepartmentServiceImpl {
	public Department addDepartment(Department department);
	public List<Department> getAllDepartment();
	public Optional<Department> getDepartmentById(Long id);      
	public boolean deleteDepartment(Long id);    
	public boolean deleteDepartmentSelected(List<Long> id); 
	public boolean deleteAllDepartment(); 
	public boolean updateDepartment(Department department, Long id); 
	public List<Department> getDepartmentByName(String name);
	public List<Department> fakeDataDepartment();
	
}
