package com.vandai.mobi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "position")
@Getter
@Setter
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String idPosition;
	@Column
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
	@JsonIgnore
	private List<Employee> employees;
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
}
