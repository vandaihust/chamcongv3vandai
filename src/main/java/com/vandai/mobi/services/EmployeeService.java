package com.vandai.mobi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vandai.mobi.dto.EmployeeDto;
import com.vandai.mobi.model.Department;
import com.vandai.mobi.model.Employee;
import com.vandai.mobi.model.Position;
import com.vandai.mobi.model.User;
import com.vandai.mobi.reponsitory.DepartmentRepository;
import com.vandai.mobi.reponsitory.EmployeeRepository;
import com.vandai.mobi.reponsitory.PositionRepository;
import com.vandai.mobi.reponsitory.UserRepository;
import com.vandai.mobi.services.impl.EmployeeServiceImpl;
@Service
public class EmployeeService implements EmployeeServiceImpl{
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	UserRepository userRepository; 
	@Autowired
	PositionRepository positionRepository;
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> listEmployee = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		return listEmployee;
	}

	@Override
	public boolean deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
		return true;	
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField) {
		
		Sort sort = Sort.by(sortField).ascending() ;
			
		//
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return employeeRepository.findAll(pageable);
		
		//ASC: A->Z
	}

	@Override
	public Employee addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		User user = userRepository.findById(employeeDto.getIdUser()).get();
		Department department = departmentRepository.findById(employeeDto.getIdDepartment()).get();
		Position position = positionRepository.findById(employeeDto.getIdPosition()).get();
		employee.setIdEmployee(employeeDto.getIdEmployee());
		employee.setName(employeeDto.getName());
		employee.setSex(employeeDto.isSex());
		employee.setBirthDate(employeeDto.getBirthDate());
		employee.setIdCardNumber(employeeDto.getIdCardNumber());
		employee.setPhone(employeeDto.getPhone());
		employee.setEmail(employeeDto.getEmail());
		employee.setMaritalStatus(employeeDto.getMaritalStatus());
		employee.setAvatar(employeeDto.getAvatar());
		employee.setAcademicLevel(employeeDto.getAcademicLevel());		
		employee.setUser(user);	
		employee.setDepartment(department);		
		employee.setPosition(position);
		
		position.addEmployee(employee);
		positionRepository.save(position);
		department.addEmployee(employee);
		departmentRepository.save(department);	//CascadeType.ALL nên ko cần save E
		return employee;
	
	}

	@Override
	public Employee getEmployeeById(long id) {
		Employee e = employeeRepository.findById(id).get();
		return e;
	}

	@Override
	public boolean deleteEmployee(long id) {	
		employeeRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean deleteEmployeeSelected(List<Long> id) {
		for (long idStr : id) {
			if(employeeRepository.existsById(idStr) == true) {
				employeeRepository.deleteById(idStr);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAllEmployee() {
		employeeRepository.deleteAll();
		return true;
	}

	@Override
	public boolean updateEmployee(Employee employee, long id) {
		employee.setId(id);
		employeeRepository.save(employee);
		return true;
	
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
//		Sort sort =  Sort.by("name").descending();//Z-->A
		Sort sort =  Sort.by("name").ascending();
		List<Employee> listEmployee = employeeRepository.findByNameIsContaining(name, sort);
		System.out.println(Sort.Direction.ASC.name());
		return listEmployee;
	}


	@Override
	public List<Employee> getEmployeeByDepartment(Long idDepartment) {
		Department department = departmentRepository.findById(idDepartment).get();
		List<Employee> listEmployee = employeeRepository.findByDepartment(department);
		return listEmployee;
	}


}
