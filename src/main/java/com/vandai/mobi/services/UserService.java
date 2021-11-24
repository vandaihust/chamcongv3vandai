package com.vandai.mobi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandai.mobi.model.User;
import com.vandai.mobi.reponsitory.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public User addUser(User user) {
		userRepository.save(user);
		return user;
	}
	public User getById(Long id) {
		User user = userRepository.findById(id).get();
		return user;
	}
}
