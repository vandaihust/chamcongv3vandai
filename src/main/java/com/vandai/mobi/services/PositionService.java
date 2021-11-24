package com.vandai.mobi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandai.mobi.model.Employee;
import com.vandai.mobi.model.Position;
import com.vandai.mobi.reponsitory.EmployeeRepository;
import com.vandai.mobi.reponsitory.PositionRepository;
import com.vandai.mobi.services.impl.PositionServiceImpl;

@Service
public class PositionService implements PositionServiceImpl{
	@Autowired
	PositionRepository positionRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public Position addPosition(Position position) {
		positionRepository.save(position);
		return position;
	}

	@Override
	public List<Position> getAllPosition() {
		List<Position> postList = positionRepository.findAll();
		return postList;
	}

	@Override
	public Optional<Position> getPositionById(int id) {
		Optional<Position> position = positionRepository.findById(id);
		return position;
	}

	@Override
	public boolean deletePosition(int id) {
		
		if(positionRepository.findById(id).get().getIdPosition().equals("CVT")) return true;
		List<Employee> list = employeeRepository.findByPosition(positionRepository.findById(id).get());
		for (Employee employee : list) {
			employee.setPosition(positionRepository.findByIdPosition("CVT"));
			employeeRepository.save(employee);
		}
		positionRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean deletePositionSelected(List<Integer> id) {
		for (int idStr : id) {
			if(positionRepository.existsById(idStr) == true) {
				positionRepository.deleteById(idStr);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAllPosition() {
		positionRepository.deleteAll();
		return true;
	}

	@Override
	public boolean updatePosition(Position position, int id) {
		position.setId(id);
		positionRepository.save(position);
		return true;
	}

	@Override
	public List<Position> getPositionByName(String name) {
		List<Position> positions = positionRepository.findByNameIsContaining(name);
		return positions;
	}

	@Override
	public List<Position> fakeDataPosition() {
		List<Position> list = new ArrayList<Position>();
		for(long i = 1; i < 6; i++) {
			Position position = new Position();
			position.setIdPosition("CV"+i);
			position.setName("Chức vụ số "+i);
			list.add(position);
			positionRepository.save(position);			
		}
		return list;
	}
	

}
