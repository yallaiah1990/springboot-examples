package com.employeemngt.gateway.jwt;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("venkata".equals(username)) {
			return new User("venkata", "venk@t@"/*"$2a$10$fhYkfu6dAAKUR/Emo1ONseTZydgvvM17H4RZAc2z51RVHxB4RFgj6"*/,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}