package com.vandai.mobi.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandai.mobi.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>{
	public List<Position> findByNameIsContaining(String name);
	public Position findByIdPosition(String idPosition);
}
