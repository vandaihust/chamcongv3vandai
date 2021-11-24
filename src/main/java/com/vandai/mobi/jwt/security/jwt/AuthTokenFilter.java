package com.vandai.mobi.jwt.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vandai.mobi.jwt.security.services.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter{
	@Autowired
	private JwtUtils jwtUtils; //autowire bên create jwt
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;//load user
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);//đưa ra các hiển thị trên console
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		//lấy thừ ký tự thứ 7 bỏ đi type là bearer ở jwtresponse
		//kiểm tra xem có phải là String và bắt đầu là bearer parst l
		return null;
	}
	
}
//UsernamePasswordAuthenticationToken gets {username, password} from login Request, 
//AuthenticationManager will use it to authenticate a login account.

//Có nhiệm vụ kiểm tra request của người dùng trước khi nó tới đích.
//Nó sẽ lấy Header Authorization ra và kiểm tra xem chuỗi JWT người dùng 
//gửi lên có hợp lệ không.