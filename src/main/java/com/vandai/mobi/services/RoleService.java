package com.vandai.mobi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandai.mobi.model.Role;
import com.vandai.mobi.reponsitory.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	public Role addRole(Role role) {
		roleRepository.save(role);
		return role;
	}
}
