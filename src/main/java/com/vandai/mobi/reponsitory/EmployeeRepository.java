package com.vandai.mobi.reponsitory;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vandai.mobi.model.Department;
import com.vandai.mobi.model.Employee;
import com.vandai.mobi.model.Position;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public List<Employee> findByName(String name);
	public List<Employee> findByDepartment(Department department);
	public List<Employee> findByPosition(Position position);
	public List<Employee> findByNameIsContaining(String name, Sort sort);
}
