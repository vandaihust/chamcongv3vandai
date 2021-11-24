package com.vandai.mobi.services.impl;

import java.util.List;
import java.util.Optional;
import com.vandai.mobi.model.Position;

public interface PositionServiceImpl {
	public Position addPosition(Position position);
	public List<Position> getAllPosition();
	public Optional<Position> getPositionById(int id);      
	public boolean deletePosition(int id);    
	public boolean deletePositionSelected(List<Integer> id); 
	public boolean deleteAllPosition(); 
	public boolean updatePosition(Position position, int id); 
	public List<Position> getPositionByName(String name);
	public List<Position> fakeDataPosition();
}
