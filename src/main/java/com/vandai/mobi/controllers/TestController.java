package com.vandai.mobi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.mobi.model.ERole;
import com.vandai.mobi.model.Role;
import com.vandai.mobi.services.RoleService;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "*")
public class TestController {
	@Autowired
	RoleService roleService;
	@GetMapping("/all")
	public String allAccess() {
		Role role = new Role();
		Role role2 = new Role();
		Role role3 = new Role();
		role.setName(ERole.ROLE_ADMIN);roleService.addRole(role);
		role2.setName(ERole.ROLE_SUPPORT);roleService.addRole(role2);
		role3.setName(ERole.ROLE_USER);
		roleService.addRole(role3);
		return "Thêm role thành công";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('SUPPORT') or hasRole('ADMIN')")
	public String userAccess() {
		return "Trang dành cho user";
	}

	@GetMapping("/support")
	@PreAuthorize("hasRole('SUPPORT') or hasRole('ADMIN')")
	public String moderatorAccess() {
		return "Trang dành cho support";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Trang dành cho admin.";
	}
}
