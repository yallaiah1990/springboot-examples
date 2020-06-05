package com.employeemngt.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.employeemngt.exception.UserAuthException;
import com.employeemngt.util.ErrorCodes;

@Controller
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/generateToken", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		return new ResponseEntity<>(new JwtResponse(jwtTokenUtil.generateToken(userDetails)), HttpStatus.OK);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String loginmethod(JwtRequest authenticationRequest, HttpServletResponse httpServletResponse) {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		CookieUtil.create(httpServletResponse, "JWT-TOKEN", token, false, -1, "localhost");

		return "index";

	}

	@RequestMapping(value = "/SignOut", method = RequestMethod.GET)
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		CookieUtil.clear(httpServletResponse, "JWT-TOKEN");
		return "logout";
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UserAuthException("USER_DISABLED", e, ErrorCodes.NOT_FOUND);
		} catch (BadCredentialsException e) {
			throw new UserAuthException("INVALID_CREDENTIALS", e, ErrorCodes.NOT_AUTHORIZED);
		}
	}

	/*@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User create(@RequestBody RequestObject requestObject) {
		
		//return userService.save(user);
	}*/
}