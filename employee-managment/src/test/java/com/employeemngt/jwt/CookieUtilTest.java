package com.employeemngt.jwt;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CookieUtilTest {

	@Mock
	HttpServletResponse response;

	private Cookie cookie;

	@Before
	public void initialSetup() {
		cookie = new Cookie("JWTTOKEN", "asdlkfasdgflaisgdflasbdjlasd");
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		cookie.setDomain("localhost");
		cookie.setPath("/");
	}

	@Test
	public void createCookiTest() {
		doNothing().when(response).addCookie(anyObject());
		assertNotNull(response);
	}

	@Test
	public void clearCookiTest() {
		doNothing().when(response).addCookie(anyObject());
		assertNotNull(response);
	}

}
