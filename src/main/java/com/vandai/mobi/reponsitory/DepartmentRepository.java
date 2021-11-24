package com.vandai.mobi.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandai.mobi.model.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	public List<Department> findByNameIsContaining(String name);
	public Department findByIdDepartment(String idDepartment);
}
