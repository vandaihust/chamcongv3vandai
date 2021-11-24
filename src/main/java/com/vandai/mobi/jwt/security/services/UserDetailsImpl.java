package com.vandai.mobi.jwt.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandai.mobi.model.User;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;
//	Nói một cách đơn giản, chúng ta sử dụng thuộc tính serialVersionUID 
//	để ghi nhớ các phiên bản của một Serializable class (class implement Serializable interface) 
//	để xác minh một object được tuần tự hoá trước đó 
//	có tương thích với Serializable class ở phiên bản hiện tại hay không.
//
//	Các thuộc tính serialVersionUID của các class khác nhau độc lập,
//	không liên qua đến nhau. 
//	Do đó, các class khác nhau có thể có cùng giá trị serialVersionUID.
	
	private Long id;
	private String username;
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;//các quyền
	
	
	public UserDetailsImpl(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return  new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}
	public Long getId() {
		return id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) obj;
		return Objects.equals(id, user.id);
	}

}
