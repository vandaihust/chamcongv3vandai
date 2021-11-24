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

import com.vandai.mobi.model.Position;
import com.vandai.mobi.services.PositionService;

@RestController
@RequestMapping("api/position")
@CrossOrigin("*")
public class PositionController {
	@Autowired
	PositionService PositionService;
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllPosition(){
		PositionService.fakeDataPosition();
		List<Position> listPosition = PositionService.getAllPosition();
		return new ResponseEntity<List<Position>>(listPosition, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getPositionById(@PathVariable int id){
		Optional<Position> Position = PositionService.getPositionById(id);		
		return new ResponseEntity<Optional<Position>>(Position, HttpStatus.OK);
	}
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addPosition(@RequestBody Position Position){
		return new ResponseEntity<Position>(PositionService.addPosition(Position)
				, HttpStatus.OK);
	}
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updatePosition(@RequestBody Position Position,@PathVariable int id){
		return new ResponseEntity<>(PositionService.updatePosition(Position, id)
				, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deletePosition(@PathVariable int id){
		return new ResponseEntity<>(PositionService.deletePosition(id)
				, HttpStatus.OK);
	}
	@DeleteMapping("all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAllPosition(){
		return new ResponseEntity<>(PositionService.deleteAllPosition()
				, HttpStatus.OK);
	}
//	@DeleteMapping("{ids}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> deletePositionSelected(@PathVariable List<String> ids){
//		PositionService.deletePositionSelected(ids);
//		return ResponseEntity.ok().body("Delete selected Position success");
//	}
//	
	@GetMapping("search/{name}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchPosition(@PathVariable String name){
		List<Position> listPosition = PositionService.getPositionByName(name);
		return new ResponseEntity<List<Position>>(listPosition, HttpStatus.OK);
	}
}
