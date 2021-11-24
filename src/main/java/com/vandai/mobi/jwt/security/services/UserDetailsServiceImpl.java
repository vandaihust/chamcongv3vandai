package com.vandai.mobi.jwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vandai.mobi.model.User;
import com.vandai.mobi.reponsitory.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
		
//		interface has a method to load User by username and 
//		returns a UserDetails object that Spring Security can use for authentication and validation.
//
//		– UserDetails contains necessary information 
//		(such as: username, password, authorities) to build an Authentication object.
	}

}
